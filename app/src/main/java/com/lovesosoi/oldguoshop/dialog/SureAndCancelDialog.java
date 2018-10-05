package com.lovesosoi.oldguoshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovesosoi.oldguoshop.R;


public class SureAndCancelDialog extends Dialog implements View.OnClickListener {

    private OnClickChoose onClickChoose;
    private String title;
    private String cancel = "取消";
    private String confirm = "确认";

    public SureAndCancelDialog(Context context, String msg) {
        super(context, R.style.surecancel_dialog);
        this.title = msg;
    }

    public SureAndCancelDialog(Context context, String msg, String cancel_txt, String confirm_txt) {
        super(context, R.style.surecancel_dialog);
        this.title = msg;
        this.cancel = cancel_txt;
        this.confirm = confirm_txt;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sure_cancel);

        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_content.setText(title);
        tv_cancel.setText(cancel);
        tv_sure.setText(confirm);
        tv_cancel.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }

    public void setOnClickChoose(OnClickChoose onClickChoose) {
        this.onClickChoose = onClickChoose;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                if (onClickChoose != null) {
                    onClickChoose.onClick(false);
                }
                dismiss();
                break;
            case R.id.tv_sure:
                if (onClickChoose != null) {
                    onClickChoose.onClick(true);
                }
                dismiss();
                break;
        }
    }

    public interface OnClickChoose {
        void onClick(boolean f);
    }
}
