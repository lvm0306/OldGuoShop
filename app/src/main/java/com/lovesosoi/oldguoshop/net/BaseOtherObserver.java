package com.lovesosoi.oldguoshop.net;

import android.app.Activity;
import android.content.DialogInterface;

import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.base.IBaseView;
import com.lookbi.baselib.net.ExceptionHelper;
import com.lookbi.baselib.utils.DialogUtils;
import com.lookbi.baselib.utils.LogUtil;
import com.lookbi.baselib.utils.SPUtil;
import com.lovesosoi.oldguoshop.utils.UtilTools;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 非标准格式数据 处理
 * 如百度返回的数据：
 * <p>
 * {   "place_info":{},
 * "content":[],
 * "current_city":[],
 * "result":{},
 * "damoce":null
 * }
 * <p>
 * Created by zhangyisheng on 2017/11/24.
 */

public abstract class BaseOtherObserver<T> implements Observer<T> {

    private static final String TAG = "TAG--BaseObserver";
    private Activity mContext;
    private boolean isShowLoading = true;
    private String loadingMsg = "";
    private boolean isCanDispose = true;
    private Disposable mDisposable;
    private boolean isRefershOrLoadmore = false;
    private boolean isShowErrorToast = true;
    private int mCacheKey = -1;
    private BasePresenterImpl presenter;

//    public BaseOtherObserver(Activity context) {
//        this.mContext = context;
//    }

    public BaseOtherObserver(Activity context, BasePresenterImpl presenter) {
        this.mContext = context;
        this.presenter=presenter;
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
            if (!isRefershOrLoadmore) {
                if (isShowLoading) {
                    if (isCanDispose) {
                        DialogUtils.show(mContext, loadingMsg, new DialogInterface
                                .OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                if (null != d && !d.isDisposed()) {//随着dialog消失，如果订阅了就取消订阅
                                    d.dispose();
                                    onEnd();
                                    LogUtil.e("BaseObserver-dispose");
                                }
                            }
                        });
                    } else {
                        DialogUtils.show(mContext, loadingMsg);
                    }

                }
            }

            onStart(d);
        }
    }

    @Override
    public void onNext(T value) {
        LogUtil.e("onNext-" + value);
        DialogUtils.dismiss();
        try {
            onEnd();
            onSuccess(value);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {

        LogUtil.e("onError");

        DialogUtils.dismiss();
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
            }
        }
        onEnd();
    }

    @Override
    public void onComplete() {
        LogUtil.e("onComplete");
        if (null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
//        DialogUtils.dismiss();
//        onEnd();
    }

    protected void onStart(Disposable disposable) {
    }

    protected abstract void onSuccess(T t);

    protected void onError(String msg) {
        if (presenter != null) {
            if (presenter.isViewAttached()) {
                ((IBaseView) presenter.getView()).httpError(msg);
            }
        }
    };

    protected void onEnd() {
        if (presenter != null) {
            if (presenter.isViewAttached()) {
                ((IBaseView) presenter.getView()).requestEnd();
            }
        }

    };



    public Activity getmContext() {
        return mContext;
    }

    public void setmContext(Activity mContext) {
        this.mContext = mContext;
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

    public boolean isCanDispose() {
        return isCanDispose;
    }

    public void setCanDispose(boolean canDispose) {
        isCanDispose = canDispose;
    }

    public Disposable getDisposable() {
        return mDisposable;
    }

    public void setmDisposable(Disposable mDisposable) {
        this.mDisposable = mDisposable;
    }

    public boolean isRefershOrLoadmore() {
        return isRefershOrLoadmore;
    }

    public void setRefershOrLoadmore(boolean refershOrLoadmore) {
        isRefershOrLoadmore = refershOrLoadmore;
    }

    public boolean isShowErrorToast() {
        return isShowErrorToast;
    }

    public void setShowErrorToast(boolean showErrorToast) {
        isShowErrorToast = showErrorToast;
    }

    public int getCacheKey() {
        return mCacheKey;
    }

    public void setCacheKey(int mCacheKey) {
        this.mCacheKey = mCacheKey;
    }
}
