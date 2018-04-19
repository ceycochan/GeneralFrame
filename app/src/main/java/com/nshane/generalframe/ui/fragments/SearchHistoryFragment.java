package com.nshane.generalframe.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;

/**
 * Created by bryan on 2018-4-2.
 */

public class SearchHistoryFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_search_history, container, false);
        } else {
            //防止重新加载,导致出现闪退 /
            if (null != rootView) {
                ViewGroup parent = (ViewGroup) rootView.getParent();
                if (null != parent) {
                    parent.removeView(rootView);
                }
            }
        }


        //在return rootView 获取数据


        return rootView;
    }


    private void init() {

    }


}
