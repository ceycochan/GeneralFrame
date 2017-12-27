package com.nshane.generalframe.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;


public class HomeFragment extends AbsFragment {

    private LinearLayout edittextbody;
    private EditText editText;
    private ImageView sendIv;
    private TextView title;

    private RelativeLayout bodyLayout;

    public final static int TYPE_PULLREFRESH = 1;
    public final static int TYPE_UPLOADREFRESH = 2;
    public final static int TYPE_PUBLIC = 3;
    public final static int TYPE_FOLLOW = 4;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;

    private boolean isRefresh = false;


    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container);
        initUploadDialog();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_square;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }


    private void initUploadDialog() {
    }

    @Override
    public void initView() {
    }


}
