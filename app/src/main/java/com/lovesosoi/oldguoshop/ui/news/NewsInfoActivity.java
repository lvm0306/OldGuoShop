package com.lovesosoi.oldguoshop.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lovesosoi.oldguoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsInfoActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_news_info;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        initBlackToolbar(toolbar);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }
}
