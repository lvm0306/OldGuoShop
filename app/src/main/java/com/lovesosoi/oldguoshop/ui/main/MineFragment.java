package com.lovesosoi.oldguoshop.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lookbi.baselib.base.BaseFragment;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.views.CircleImageView;
import com.lovesosoi.oldguoshop.R;
import com.lovesosoi.oldguoshop.ui.contract_us.ContractUsActivity;
import com.lovesosoi.oldguoshop.ui.mine_info.MineInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_pic)
    CircleImageView ivPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_myinfo)
    RelativeLayout rlMyinfo;
    @BindView(R.id.rl_tbc_order)
    RelativeLayout rlTbcOrder;
    @BindView(R.id.rl_complete_order)
    RelativeLayout rlCompleteOrder;
    @BindView(R.id.rl_forget_psw)
    RelativeLayout rlForgetPsw;
    @BindView(R.id.rl_connect_us)
    RelativeLayout rlConnectUs;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        ImmersionBar.with(this).statusBarColor(R.color.main_color).statusBarDarkFont(true, 0.2f).init();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected int getFristTopViewId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_myinfo, R.id.rl_tbc_order, R.id.rl_complete_order, R.id.rl_forget_psw, R.id.rl_connect_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_myinfo:
                startActivity(MineInfoActivity.class);
                //我的信息
                break;
            case R.id.rl_tbc_order:
                //未完成订单
                break;
            case R.id.rl_complete_order:
                //已完成订单
                break;
            case R.id.rl_forget_psw:
                //忘记密码
                break;
            case R.id.rl_connect_us:
                //联系我们
                startActivity(ContractUsActivity.class);
                break;
        }
    }
}
