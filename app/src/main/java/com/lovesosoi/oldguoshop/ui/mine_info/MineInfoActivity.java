package com.lovesosoi.oldguoshop.ui.mine_info;

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

public class MineInfoActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }
}
