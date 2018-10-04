package com.lovesosoi.oldguoshop.net;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.base.IBaseView;
import com.lookbi.baselib.bean.BaseBean;
import com.lookbi.baselib.event.EventBusUtil;
import com.lookbi.baselib.net.ExceptionHelper;
import com.lookbi.baselib.utils.DialogUtils;
import com.lookbi.baselib.utils.LogUtil;
import com.lookbi.baselib.utils.SPUtil;
import com.lookbi.womenprison.AppContext;
import com.lookbi.womenprison.constant.EventConstant;
import com.lookbi.womenprison.constant.NoCodeConstant;
import com.lookbi.womenprison.ui.login.LoginActivity;
import com.lookbi.womenprison.utils.UtilTools;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 返回的是标准格式数据处理
 * 如：
 * { "data":{},
 * "error":"",
 * "status":1
 * }
 * Created by zhangyisheng on 2017/11/24.
 */

public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {

    private static final String TAG = "TAG--BaseObserver";
    private Activity mContext;
    private boolean isShowLoading = true;
    private boolean isCanDispose = false;//是否可以手动取消网络请求（取消订阅）
    private String loadingMsg = "";
    private Disposable mDisposable;
    private boolean isRefershOrLoadmore = false;
    private boolean isShowErrorToast = true;
    private boolean isFristRequest = true;
    private boolean isLastRequest = true;
    private int mCacheKey = -1;//缓存key为-1时，不使用缓存
    private BasePresenterImpl presenter;

//    public BaseObserver(Activity context) {
//        this.mContext = context;
//    }

    public BaseObserver(Activity context, BasePresenterImpl presenter) {
        this.mContext = context;
        this.presenter = presenter;
    }

    @Override
    public void onSubscribe(final Disposable d) {
        mDisposable = d;
        if (!UtilTools.isNetWorkConnected(mContext)) {
            try {
                if (mCacheKey != -1) {
                    T t = (T) SPUtil.getObject(mContext, mCacheKey + "");
                    onSuccess(t);
                } else {
                    if (null != mDisposable && !mDisposable.isDisposed()) {
                        mDisposable.dispose();
                    }
                    onError("请检查您的网络");
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (null != mDisposable && !mDisposable.isDisposed()) {
                    mDisposable.dispose();
                }
                onError("请检查您的网络");
            }
            onEnd();
        } else {
            onStart(d);
            if (!isRefershOrLoadmore) {
                if (isShowLoading) {
                    if (isCanDispose) {
                        if (isFristRequest) {
                            DialogUtils.show(mContext, loadingMsg, new DialogInterface
                                    .OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if (null != d && !d.isDisposed()) {//随着dialog消失，如果订阅了就取消订阅
                                        d.dispose();
                                        onEnd();
                                        LogUtil.e("onDismiss-BaseObserver-dispose");
                                    }
                                }
                            });
                        }
                    } else {
                        if (isFristRequest) {
                            DialogUtils.show(mContext, loadingMsg);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onNext(BaseBean<T> value) {
        if (isLastRequest) {
            DialogUtils.dismiss();
        }
        try {
            if (value.getError().isEmpty()) {
                onEnd();
                T t = value.getData();
                onSuccess(t);
            } else {
                if (value.getStatus() == 0) {
                    EventBusUtil.post(EventConstant.LOGOUT_SUCCESS);
                    AppContext.getInstance().setToken(null);
                    AppContext.getInstance().setMember(null);
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
//                    mContext.finish();
                }
                onError(value.getError());
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (isLastRequest) {
            DialogUtils.dismiss();
        }

        String err = "";
        if (!UtilTools.isNetWorkConnected(mContext)) {
            err = "请检查您的网络";
        } else {
            err = ExceptionHelper.handleException(e);
        }
        if (!err.isEmpty()) {
            try {
                onError(err);
            } catch (Exception e1) {
                e1.printStackTrace();
                onError(e1.getMessage());
            }
        }
        onEnd();
//        if (null != mDisposable && !mDisposable.isDisposed()) {
//            mDisposable.dispose();
//        }
    }

    @Override
    public void onComplete() {
//        DialogUtils.dismiss();
//        onEnd();
        if (null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    protected void onStart(Disposable disposable) {
        LogUtil.e("onStart");
    }

    protected abstract void onSuccess(T t);

    protected void onError(String msg) {
        if (presenter != null) {
            if (presenter.isViewAttached()) {
                ((IBaseView) presenter.getView()).httpError(msg);
                ((IBaseView) presenter.getView()).noData(NoCodeConstant.NODATA);
            }
        }
    }

    ;

    protected void onEnd() {
        if (presenter != null) {
            if (presenter.isViewAttached()) {
                ((IBaseView) presenter.getView()).requestEnd();
            }
        }

    }

    ;


    public boolean isCanDispose() {
        return isCanDispose;
    }

    public void setCanDispose(boolean canDispose) {
        isCanDispose = canDispose;
    }

    public boolean isRefershOrLoadmore() {
        return isRefershOrLoadmore;
    }

    public void setRefershOrLoadmore(boolean refershOrLoadmore) {
        isRefershOrLoadmore = refershOrLoadmore;
    }

    public boolean isFristRequest() {
        return isFristRequest;
    }

    public void setFristRequest(boolean fristRequest) {
        isFristRequest = fristRequest;
    }

    public boolean isLastRequest() {
        return isLastRequest;
    }

    public void setLastRequest(boolean lastRequest) {
        isLastRequest = lastRequest;
    }

    public boolean isShowErrorToast() {
        return isShowErrorToast;
    }

    public void setShowErrorToast(boolean showErrorToast) {
        isShowErrorToast = showErrorToast;
    }


    public boolean isShowLoading() {
        return isShowLoading;
    }

    public void setShowLoading(boolean showLoading) {
        isShowLoading = showLoading;
    }

    public String getLoadingMsg() {
        return loadingMsg;
    }

    public void setLoadingMsg(String loadingMsg) {
        this.loadingMsg = loadingMsg;
    }

    public int getCacheKey() {
        return mCacheKey;
    }

    public void setCacheKey(int mCacheKey) {
        this.mCacheKey = mCacheKey;
    }
}
