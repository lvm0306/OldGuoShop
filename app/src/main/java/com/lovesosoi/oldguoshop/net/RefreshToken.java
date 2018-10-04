package com.lovesosoi.oldguoshop.net;

import android.app.Activity;
import android.media.session.MediaSession;

import com.lookbi.baselib.net.RxSchedulers;
import com.lookbi.baselib.utils.LogUtil;
import com.lovesosoi.oldguoshop.AppContext;
import com.lovesosoi.oldguoshop.bean.Token;

import io.reactivex.Observer;


public class RefreshToken {
    public static void refreshToken(final Activity context,String token) {
//        String token = SPUtil.getString(context, "TOKEN", "");
        Observer mObserver = new BaseObserver<Token>(context, null) {
            @Override
            protected void onSuccess(Token mData) {
                AppContext.getInstance().setToken(mData);
            }
            @Override
            protected void onError(String msg) {
                LogUtil.e("刷新token失败"+msg);
            }

            @Override
            protected void onEnd() {

            }
        };
        Api.getService().refresh(token,"1")
                .compose(RxSchedulers.compose(context))
                .subscribe(mObserver);
    }
}
