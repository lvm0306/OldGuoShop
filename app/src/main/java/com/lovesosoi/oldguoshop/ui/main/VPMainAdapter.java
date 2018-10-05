package com.lovesosoi.oldguoshop.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

class VPMainAdapter extends FragmentPagerAdapter {
    List<Fragment> mList;

    public VPMainAdapter(FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
