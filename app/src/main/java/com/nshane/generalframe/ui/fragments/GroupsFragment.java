package com.nshane.generalframe.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;


public class GroupsFragment extends AbsFragment {


    ViewGroup mLayoutViewMore;
    ViewGroup mLayoutMyNothing;
    boolean mDataNotify = false;
    private View mRootView;
    private Activity mActivity;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GroupsFragment.class);
        context.startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mActivity = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = super.onCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    //invoked returned back fm single group only
    @Override
    public void onResume() {
        super.onResume();
        if (mDataNotify) {
            notifyAdater1();
            mDataNotify = false;
        }
    }

    private void notifyAdater1() {
//        mGroupItemAdapter1.notifyDataSetChanged();
//        if (mList1.isEmpty()) {
//            mLayoutMyNothing.setVisibility(View.VISIBLE);
//            mRecyclerView1.setVisibility(View.GONE);
//            mLayoutViewMore.setVisibility(View.GONE);
//        } else if (mList1.size() <= 10) {
//            mLayoutViewMore.setVisibility(View.GONE);
//            mRecyclerView1.setVisibility(View.VISIBLE);
//            mLayoutMyNothing.setVisibility(View.GONE);
//        }
    }

    private void initRecyclerView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_group;
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


    //monitor of scrollview bottom

    private boolean mLoadingMore = false;
    private int mLoadingPage = 1;
    private int mBottomCount = 0;

    private void initScrollView() {

    }
}

