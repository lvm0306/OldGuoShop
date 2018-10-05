package com.lovesosoi.oldguoshop.ui.main;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lookbi.baselib.base.BaseActivity;
import com.lookbi.baselib.base.BasePresenterImpl;
import com.lookbi.baselib.views.NoScrollViewPager;
import com.lovesosoi.oldguoshop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    List<Fragment> mFmList = new ArrayList<>();
    VPMainAdapter mAdapter;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFristTopViewId() {
        return R.id.view_top;
    }

    @Override
    protected void initView() {
        mImmersionBar.init();
        mFmList.add(MenuFragment.newInstance());
        mFmList.add(MineFragment.newInstance());
        mAdapter = new VPMainAdapter(getSupportFragmentManager(), mFmList);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        vpMain.setAdapter(mAdapter);

    }

    @OnClick({R.id.tv_menu, R.id.tv_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_menu:
                changeTab(1);
                break;
            case R.id.tv_mine:
                changeTab(2);
                break;
        }
    }

    private void changeTab(int tab) {
        switch (tab) {
            case 1:
                tvMenu.setTextColor(getResources().getColor(R.color.main_color));
                tvMine.setTextColor(getResources().getColor(R.color.color_333));
                Drawable drawableLeft = getResources().getDrawable(R.mipmap.ic_menu_s);
                tvMenu.setCompoundDrawablesWithIntrinsicBounds(null, drawableLeft, null, null);
                Drawable drawableright = getResources().getDrawable(R.mipmap.ic_mine_n);
                tvMine.setCompoundDrawablesWithIntrinsicBounds(null, drawableright, null, null);
                vpMain.setCurrentItem(0);
                break;
            case 2:
                tvMenu.setTextColor(getResources().getColor(R.color.color_333));
                tvMine.setTextColor(getResources().getColor(R.color.main_color));
                Drawable drawableLeft1 = getResources().getDrawable(R.mipmap.ic_menu_n);
                tvMenu.setCompoundDrawablesWithIntrinsicBounds(null, drawableLeft1, null, null);
                Drawable drawableright1 = getResources().getDrawable(R.mipmap.ic_mine_s);
                tvMine.setCompoundDrawablesWithIntrinsicBounds(null, drawableright1, null, null);
                vpMain.setCurrentItem(1);
                break;
        }
    }


}
