package com.nshane.generalframe.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nshane.generalframe.R;
import com.nshane.generalframe.adapters.TopViewPagerAdapter;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.BaseActivity;
import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.EventUtil;
import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.views.BottomIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bryan on 2017-12-21.
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "cg";
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;

    public static BottomIndicator mIndicator;
    public static boolean onLoadAllConvertsationOverFlag = false;

    public static int intoChatCount = 0;
    public static int pickBottleCount = 0;
    public static int intoUserPageCount = 0;
    public static int viewPicCount = 0;


    /**
     * @author
     * @description a collecting frame for app constructing
     */

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }


    private int mCount = 0;

    @Override
    public void initView() {

        LogUtil.d(Constants.TAG, "initView @ MainActivity");
        mTitles = Arrays.asList(getResources().getString(R.string.tab_home), getResources().getString(R.string.btn_center_chat), getResources().getString(R.string.tab_qunzu), getResources().getString(R.string.tab_my_new));
        mViewPager = (ViewPager) findViewById(R.id.id_viewPager);
        mIndicator = (BottomIndicator) findViewById(R.id.id_bottom_indicator);

        for (String title : mTitles) {
            if (title.equals(getResources().getString(R.string.tab_my_new))) {
                SettingFragment mSettingFragment = new SettingFragment();
                mFragments.add(mSettingFragment);

            } else if (title.equals(getResources().getString(R.string.tab_qunzu))) {
                GroupsFragment groupFragment = new GroupsFragment();
                mFragments.add(groupFragment);
            } else if (title.equals(getResources().getString(R.string.tab_home))) {
                HomeFragment mHomeFragment = new HomeFragment();
                mFragments.add(mHomeFragment);
            } else if (title.equals(getResources().getString(R.string.btn_center_chat))) {
                MyFragment mMyFragment = new MyFragment();
                mFragments.add(mMyFragment);
            }
            mPagerAdapter = new TopViewPagerAdapter(getSupportFragmentManager(), mFragments);

            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setOffscreenPageLimit(5);


//            mViewPager.setCurrentItem(2);  // 指定初始化
            mIndicator.setViewPager(mViewPager);
            /**
             *
             */
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        mCount++;
                        if (mCount == 4) {
                            EventBus.getDefault().post(new EventUtil("setting"));
                            mCount = 0;
                        }


                    } else if (position == 0) { // 其他碎片转入瓶子碎片加载系统通知消息
                        mCount++;
                        if (mCount == 4) {
                            EventBus.getDefault().post(new EventUtil("bottle"));
                            mCount = 0;
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
