package com.nshane.generalframe.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.interfaces.IView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bryan on 2017-12-21.
 */


// fragment base activity
public abstract class AbsFragment extends Fragment implements IView{

    private Set<IPresenter> mAllPresenters = new HashSet<IPresenter>(1);

    /** * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     *需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract IPresenter[] getPresenters();

    //初始化presenters，
    protected abstract void onInitPresenters();


    protected void parseArgumentsFromIntent(Bundle bundle){
    }

    private void addPresenters(){

        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            for(int i = 0; i < presenters.length; i++){
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(inflater, container);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootView = inflater.inflate(getLayoutResId(), container , false) ;

        Bundle bundle = getArguments() ;
        if (bundle != null){
            parseArgumentsFromIntent(bundle) ;
        }

        onInitPresenters();

        addPresenters();
        return rootView ;
    }

    @Override
    public void onResume() {
        super.onResume();
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onResume();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onDestroy();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onPause();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onStart();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onStop();
            }
        }
    }

}
