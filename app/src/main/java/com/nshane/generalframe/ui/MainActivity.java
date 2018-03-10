package com.nshane.generalframe.ui;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

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

    private static final String TAG = "dax";
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
    public static BottomIndicator mIndicator;


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

                    } else if (position == 0) { // 其他碎片转入的信息
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


    @Override
    protected void onResume() {
        super.onResume();

        checkNetWorkState();
    }

    /**
     * 检测是否网络连接
     */

    private ConnectivityManager manager;

    private boolean checkNetWorkState() {
        boolean flag = false;

        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();

        }


        if (!flag) {
            //不可用&去设置网络
            setNetWork();
        } else {
            //网络可用判断是WIFI or 4G
            netConnected();
        }

        return flag;


    }

    /**
     * 网络处于连接状态
     * <p>
     * 判断是 WIFI or 4G
     */

    private void netConnected() {
        NetworkInfo.State lte = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (lte == NetworkInfo.State.CONNECTED || lte == NetworkInfo.State.CONNECTING) {
            Toast.makeText(this, "NetWork available : LTE", Toast.LENGTH_SHORT).show();
        }

        if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
            Toast.makeText(this, "NetWork available : WIFI", Toast.LENGTH_SHORT).show();

        }

    }


    /**
     * 设置网络
     */
    private void setNetWork() {
        Toast.makeText(this, "wifi is closed", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "取消了网络设置", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();

    }


}
