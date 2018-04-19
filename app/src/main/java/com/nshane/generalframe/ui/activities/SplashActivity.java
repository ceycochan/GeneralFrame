package com.nshane.generalframe.ui.activities;

import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.BaseActivity;

/**
 * Created by bryan on 2018-4-18.
 */


// TODO: 2018-4-18 启动页面 动画 or
public class SplashActivity extends BaseActivity {
    @Override
    public void initView() {

    }

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
}
