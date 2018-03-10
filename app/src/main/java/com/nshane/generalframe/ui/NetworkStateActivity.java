package com.nshane.generalframe.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.BaseActivity;
import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.utils.NetworkStateReceiverSub;

/**
 * Created by bryan on 2018-3-9.
 */

public class NetworkStateActivity extends BaseActivity {


    NetworkStateReceiverSub networkStateReceiver;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NetworkStateActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initView() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_network_test;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (networkStateReceiver == null) {
            networkStateReceiver = new NetworkStateReceiverSub();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver, filter);
        LogUtil.d("net", "receiver注册");
    }


    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkStateReceiver);
        LogUtil.d("net", "receiver注销");
    }
}
