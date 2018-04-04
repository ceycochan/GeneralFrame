package com.nshane.generalframe.ui.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;
import com.nshane.generalframe.utils.LogUtil;

import butterknife.ButterKnife;


/**
 * Created by lzz on 2017/6/30.
 */
public class SearchFragment extends AbsFragment {

    private Resources resources;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container);

        ButterKnife.bind(this, rootView);

        resources = getResources();

        return rootView;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search;
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
