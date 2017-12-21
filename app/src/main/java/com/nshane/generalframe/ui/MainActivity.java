package com.nshane.generalframe.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 2017-12-21.
 */

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
//    public static BottomIndicator mIndicator;
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    public void initView() {

    }
}
