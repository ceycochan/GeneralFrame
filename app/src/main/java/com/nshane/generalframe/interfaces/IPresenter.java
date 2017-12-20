package com.nshane.generalframe.interfaces;

/**
 * Created by bryan on 2017-12-20.
 */

public interface IPresenter <V extends IView> {
    void onStop();
    void onResume();
    void onDestroy();
    void onPause();
    void onStart();
    void init(V view);
}
