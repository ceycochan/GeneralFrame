package com.nshane.generalframe.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;
import com.nshane.generalframe.utils.LogUtil;


/**
 * Created by lzz on 2017/6/30.
 */
public class MyFragment extends AbsFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container);
        Toolbar mTb = (Toolbar) rootView.findViewById(R.id.tb_toolbar);
        mTb.setTitle(getString(R.string.btn_center_chat));
        mTb.setTitleTextColor(getResources().getColor(R.color.colorToolBarBText));
        initHeader(rootView);
        initMeetU(rootView);
        return rootView;
    }

    private void initHeader(View rootView) {

    }


    private int mMeetUPage = 1;
    private int mFollowBottlePage = 1;
    private boolean mMeetUPage1 = false;
    private boolean mFoolowBootlePage1 = false;

    private void initMeetU(View rootView) {


    }





    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my;
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



    private boolean flags = true;

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("lzz-refresh", "onPause");
        flags = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("lzz-refresh", "onDestroy");
    }


}
