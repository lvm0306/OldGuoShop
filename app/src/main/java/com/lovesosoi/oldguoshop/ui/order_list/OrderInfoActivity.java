package com.lovesosoi.oldguoshop.ui.order_list;

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
import com.lovesosoi.oldguoshop.R;
import com.lovesosoi.oldguoshop.dialog.SureAndCancelDialog;
import com.lovesosoi.oldguoshop.ui.main.MainActivity;
import com.lovesosoi.oldguoshop.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    OrderInfoAdapter mAdapter;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_order_info;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        initBlackToolbar(toolbar);
        mAdapter = new OrderInfoAdapter();
        rvInfo.setLayoutManager(new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        rvInfo.setAdapter(mAdapter);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.view_top, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_top:
                break;
            case R.id.tv_submit:
                SureAndCancelDialog dialog = new SureAndCancelDialog(mContext, "是否确认订单？", "取消", "确认");
                dialog.setOnClickChoose(new SureAndCancelDialog.OnClickChoose() {
                    @Override
                    public void onClick(boolean f) {
                        if (f){
                            ToastUtil.show("已确认订单");
                            startActivityAndClearTop(MainActivity.class);
                        }else {
                            ToastUtil.show("在看看~~");
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    class OrderInfoAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_orderinfo_list, parent, false);
            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 12;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_num)
            TextView tvNum;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
