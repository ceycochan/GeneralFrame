package com.nshane.generalframe.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.LogUtil;

import java.util.List;

/**
 * Created by bryan on 2017-12-25.
 */

public class TopViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private Context mContext;

    public TopViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
//        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtil.d(Constants.TAG, "my position = " + position);
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
