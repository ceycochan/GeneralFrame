package com.nshane.generalframe.utils;

import android.os.Environment;

/**
 * Created by lidongxu on 17/6/24.
 */

public class Constants {

    public static final String BASE_URL = "http://api.meetu.co" ;

    public static final String SELF_PKG_NAME = "com.seabottle";
    public static final String RESULT_SUCCESS = "success";
    public static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String TUBEMATE_SDPATH = SDPATH + "//";
    public static String TUBEMATE_PATH = TUBEMATE_SDPATH;
    public static String IMAGE_PATH = TUBEMATE_PATH + "image/";

    public static final String GOOGLE_PAY_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtYyhXUUUn3sDu8RoBl9ej8PpNu33wfWDY2S6Qb5M0WNSFA6z2O9b4dbqD4T7KcdoiXlN/6poESaosyLjnahieExtqd1klayn5Bn3KQzw7tDE8LIJIv0QlF7BbmAOVBKX2EJz0k9pF/ZFOiLO3H59hmnJ8wFbjs5PxAyTpHN3GWAPgq59hxhOquvNsOJ0S6wZTJ0y3IN6X60w3Q5o2C0lQIkxkIO/H4L4fteT3tsnqu1LN7GrSieOThlbG3euiZsGaL2nD+YMCjbwlGU1JVkQLGDlAroF94+NH/vndiD090AQop9Mn9lIpl2uAqjEEHiKZu5L8yCVzsDsgrN9tI75NwIDAQAB";


    public static final String GOOGLE_PAY_PID_1 = "meetu_gold01";
    public static final String GOOGLE_PAY_PID_2 = "meetu_gold02";
    public static final String GOOGLE_PAY_PID_3 = "meetu_gold03";
    public static final String GOOGLE_PAY_PID_4 = "meetu_gold04";
    public static final boolean AD_OPEN = true;

    public static final int RESULT_ALBUM_SACE = 30 ;


    //0是聊天对话框，1是捡瓶子，2是进入个人主页，3是浏览图片
    public static final int EVENT_SHOW_AD_INTO_CHAT = 0 ;
    public static final int EVENT_SHOW_AD_PICK_BOTTLE = 1 ;
    public static final int EVENT_SHOW_AD_INTO_USER_PAGE = 2 ;
    public static final int EVENT_SHOW_AD_VIEW_PIC = 3 ;


    public static final String TAG = "cg";

    //public static final int BG[] = {R.mipmap.background_login,R.mipmap.bg_1,R.mipmap.bg_2,R.mipmap.bg_3,R.mipmap.bg_4,R.mipmap.bg_5,R.mipmap.bg_6,R.mipmap.bg_7,R.mipmap.bg_8,R.mipmap.bg_9};


}
