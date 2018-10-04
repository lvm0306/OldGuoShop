package com.lookbi.baselib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lookbi.baselib.R;

import pl.droidsonroids.gif.GifImageView;

public class LoadingDialog extends Dialog {
//    TextView tv_msg;
//    GifImageView gv_loading;
//    ProgressBar av_anim;
    String txt;
    public LoadingDialog(Context context, String txt) {
        super(context, R.style.custom_dialog);
        this.txt=txt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_view);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
//        tv_msg = (TextView) findViewById(R.id.tv_msg);
//        av_anim = (ProgressBar) findViewById(R.id.av_anim);
//        gv_loading = (GifImageView) findViewById(R.id.gv_loading);
//        if (!TextUtils.isEmpty(txt)) {
//            tv_msg.setText(txt);
//        }
    }

    public void dismissLoading() {
//        gv_loading.setVisibility(View.GONE);
        dismiss();
    }
}
