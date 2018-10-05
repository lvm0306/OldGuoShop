package com.lovesosoi.oldguoshop.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.views.CircleImageView;
import com.lovesosoi.oldguoshop.R;
import com.lovesosoi.oldguoshop.ui.order_list.OrderInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.iv_buy)
    CircleImageView ivBuy;
    Context mContext;
    TypeAdapter mTypeAdapter;
    InfoAdapter mInfoAdapter;
    LinearLayoutManager mTypeManager;
    LinearLayoutManager mInfoManager;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        mContext = this;
        initWhiteToolbar(toolbar);
        mTypeAdapter = new TypeAdapter();
        mInfoAdapter = new InfoAdapter();
        mTypeManager = new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false);
        mInfoManager = new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false);
        rvInfo.setLayoutManager(mInfoManager);
        rvInfo.setAdapter(mInfoAdapter);
        rvType.setLayoutManager(mTypeManager);
        rvType.setAdapter(mTypeAdapter);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_submit, R.id.iv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                startActivity(OrderInfoActivity.class);
                break;
            case R.id.iv_buy:
                break;
        }
    }

    class TypeAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_order_type, parent, false);
            return new TypeViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class TypeViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;

            TypeViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    class InfoAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_order_info, parent, false);
            return new InfoViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class InfoViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.ll_add)
            LinearLayout llAdd;

            InfoViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
