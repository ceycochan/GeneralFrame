package com.nshane.generalframe.ui;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nshane.generalframe.R;
import com.nshane.generalframe.ui.adapters.TopViewPagerAdapter;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.theme.ColorView;
import com.nshane.generalframe.ui.abs.BaseActivity;
import com.nshane.generalframe.ui.fragments.GroupsFragment;
import com.nshane.generalframe.ui.fragments.HomeFragment;
import com.nshane.generalframe.ui.fragments.SearchFragment;
import com.nshane.generalframe.ui.fragments.SettingFragment;
import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.EventUtil;
import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.utils.Utils;
import com.nshane.generalframe.views.BottomIndicator;
import com.nshane.generalframe.views.ResideLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bryan on 2017-12-21.
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "dax";
    @BindView(R.id.main_rl)
    ResideLayout mainRl;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.fuli)
    TextView fuli;
    @BindView(R.id.android)
    TextView android;
    @BindView(R.id.ios)
    TextView ios;
    @BindView(R.id.video)
    TextView video;
    @BindView(R.id.front)
    TextView front;
    @BindView(R.id.resource)
    TextView resource;
    @BindView(R.id.app)
    TextView app;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.test1)
    TextView test1;
    @BindView(R.id.test2)
    TextView test2;
    @BindView(R.id.test3)
    TextView test3;
    @BindView(R.id.test4)
    TextView test4;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.theme)
    TextView theme;
    @BindView(R.id.status_bar)
    ColorView statusBar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
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
        ButterKnife.bind(this);
        LogUtil.d(Constants.TAG, "initView @ MainActivity");

        setTranslucentStatus(true);

//       判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            statusBar.setVisibility(View.VISIBLE);
            //还有设置View的高度，因为每个型号的手机状态栏高度都不相同
            statusBar.getLayoutParams().height = Utils.getStatusHeight(this);
            statusBar.setLayoutParams(statusBar.getLayoutParams());
        } else {
            statusBar.setVisibility(View.GONE);
        }


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
                SearchFragment mSearchFragment = new SearchFragment();
                mFragments.add(mSearchFragment);
            }


            mPagerAdapter = new TopViewPagerAdapter(getSupportFragmentManager(), mFragments);

            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setOffscreenPageLimit(5);


//            mViewPager.setCurrentItem(2);  // 指定初始化 or 指定显示某个Fragment
            mIndicator.setViewPager(mViewPager);


            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        LogUtil.d("cg", "Chat");
                        mCount++;
                        if (mCount == 4) {
                            EventBus.getDefault().post(new EventUtil("setting"));
                            mCount = 0;
                        }

                    } else if (position == 0) { // 其他碎片转入的信息
                        mCount++;

                        LogUtil.d("cg", "HHE");

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

    // 检查当前网络状态
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
     * 网络处于连接状态 /
     * 判断是 WIFI or 运营商网络
     */

    private void netConnected() {
        State lte = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (lte == State.CONNECTED || lte == State.CONNECTING) {
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
                if (Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
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


    @Override
    public void onBackPressed() {
        if (mainRl.isOpen()) {
            mainRl.closePane();
        } else {
            super.onBackPressed();
        }

    }

    @OnClick({R.id.all, R.id.fuli, R.id.android, R.id.ios, R.id.video, R.id.front, R.id.resource, R.id.app, R.id.more, R.id.test1, R.id.test2, R.id.test3, R.id.test4, R.id.about, R.id.theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all:
                toastTest("all");
                break;
            case R.id.fuli:
                break;
            case R.id.android:
                break;
            case R.id.ios:
                break;
            case R.id.video:
                break;
            case R.id.front:
                break;
            case R.id.resource:
                break;
            case R.id.app:
                break;
            case R.id.more:
                break;
            case R.id.test1:
                break;
            case R.id.test2:
                break;
            case R.id.test3:
                break;
            case R.id.test4:
                break;
            case R.id.about:
                break;
            case R.id.theme:
                break;
            default:
                break;
        }
    }


    private void toastTest(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
