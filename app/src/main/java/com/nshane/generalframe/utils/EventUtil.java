package com.nshane.generalframe.utils;

import android.graphics.Bitmap;

/**
 * Created by bryan on 2017-12-25.
 */

public class EventUtil {
    private String msg;

    private String rcdKey;

    private Bitmap bitmap;

    private String key;
    private boolean isUpdate;


    public EventUtil(String msg) {
        this.msg = msg;
    }

    public EventUtil(String msg, String key) {
        this.msg = msg;
        this.key = key;
    }


    public EventUtil(Bitmap bitmap, String rcdKey) {
        this.bitmap = bitmap;
        this.rcdKey = rcdKey;

    }


    public EventUtil(Bitmap bitmap) {
        this.bitmap = bitmap;

    }

    public EventUtil(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getKey() {
        return this.key;
    }

    public String getReloadKey() {
        return this.rcdKey;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean isUpdate() {
        return isUpdate;
    }
}
