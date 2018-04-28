package com.nshane.generalframe.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IOnItemClickListener;
import com.nshane.generalframe.ui.adapters.SearchHotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 2018-4-2.
 */

public class SearchHotFragment extends Fragment implements IOnItemClickListener{

    private View rootView;
    private RecyclerView rvHot;
    private SearchHotAdapter searchHotAdapter;
    private List<String> hotList = new ArrayList<>();


    private void setHot() {
        for (int i = 0; i < 15; i++) {
            hotList.add("keyword" + i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_search_hot, container, false);
        } else {
            //防止重新加载,导致出现闪退
            if (null != rootView) {
                ViewGroup parent = (ViewGroup) rootView.getParent();
                if (null != parent) {
                    parent.removeView(rootView);
                }
            }
        }

        // all data initializing should be done b4 return rootView

        init();


        return rootView;
    }

    private void init() {
        setHot();

        rvHot = (RecyclerView) rootView.findViewById(R.id.rv_search_hot);
        rvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        searchHotAdapter= new SearchHotAdapter(getContext(),hotList);
        rvHot.setAdapter(searchHotAdapter);
        searchHotAdapter.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(String str) {

    }

    @Override
    public void onItemDeleteClick(String str) {

    }
}
