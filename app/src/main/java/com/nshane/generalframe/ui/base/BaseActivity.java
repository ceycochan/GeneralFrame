package com.nshane.generalframe.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.interfaces.IView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bryan on 2017-12-21.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView{

    /**
     * BaseActivity initialize process:
     * 1.
     */

   private Set<IPresenter> mAllPresenters = new HashSet<>(1); // 1?


    /**
     * @关于protected
     * protected应该是用在子类实现父类的方法或者方法中使用。
     * 如果用public的，别的类还是可以使用到这个父类的方法或者成员;
     * 当使用protected的时候，只有子类能访问了，其他的类就访问不了了。
     */

    protected abstract int getLayoutResId();


     // 需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter

    protected abstract IPresenter[] getPresenters();

    //初始化presenters
    protected abstract void onInitPresenters();


    private void addPresenters(){
        IPresenter[] presenters = getPresenters();
        if (presenters!=null){
            for (int i=0;i<presenters.length;i++){
                mAllPresenters.add(presenters[i]);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        //log 执行顺序
        onInitPresenters();
        addPresenters();
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
        //MobclickAgent.onPageStart(this.getClass().getName());
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onResume();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onDestroy();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onPause();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStart();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStop();
            }
        }
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }


}
