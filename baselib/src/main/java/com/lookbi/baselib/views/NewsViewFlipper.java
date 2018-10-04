package com.lookbi.baselib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;


import com.lookbi.baselib.R;
import com.lookbi.baselib.utils.LogUtil;

import java.util.List;

/**
 * Created by ZYS on 2017/5/14.
 */

public class NewsViewFlipper extends ViewFlipper {
    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 4000;
    /**
     * 动画时间
     */
    private int animDuration = 500;

    public NewsViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);
        startAnim();
    }


    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(final List<View> views) {
        if (views == null || views.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            final int position = i;

            /**
             * 这边的监听事件是对滚动的每一条的点击事件，在使用的时候可以打开，但是在打开的时候会获取此布局的焦点，导致外层的事件不能够点击，所以在使用的时候要注意
             */

            //设置监听回调
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, views.get(position));
                    }
                }
            });
            addView(views.get(i));
        }
        startFlipping();
    }
//
    /**
     * 点击
     */
    private OnItemClickListener onItemClickListener;

    /**
     * 设置监听接口
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item_view的接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isPlay = false;
        LogUtil.e("onDetachedFromWindow");

    }

    boolean isPlay = true;

    public boolean isPlay() {
        return isPlay;
    }

    public void startAnim() {
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) {
            animIn.setDuration(animDuration);
        }
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) {
            animOut.setDuration(animDuration);
        }
        setOutAnimation(animOut);
        isPlay=true;
    }
}
