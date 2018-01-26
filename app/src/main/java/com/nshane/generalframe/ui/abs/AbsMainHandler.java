package com.nshane.generalframe.ui.abs;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


/**
 * Created by bryan on 2017-12-27.
 */

public abstract class AbsMainHandler<T> extends Handler {
    private WeakReference<T> mHost = null;

    public AbsMainHandler(T host) {
        mHost = new WeakReference<T>(host);
    }

    /**
     * the method with final can not be override !!!
     *
     * @param msg
     */
    @Override
    public final void handleMessage(Message msg) {
        if (mHost == null) {
            return;
        }

        T t = mHost.get();
        if (t == null) {
            return;
        }

        onHandleMessage(msg, t);
    }


    public abstract void onHandleMessage(Message msg, T t);
}
