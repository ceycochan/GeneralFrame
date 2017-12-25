package com.nshane.generalframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nshane.generalframe.GeneralFrameApplication;
import com.nshane.generalframe.model.entity.UserInfo;


/**
 * Created by lzz on 2016/6/15.
 */
public class SharePreferenceManager {
    /**
     * 定义用于SETTING_DOMAIN的xml文件名称，以及xml文件的所有键名与对应的默认键值
     */


    //1.  dd0771daf1104e828b95f18c034a6e09  @ fann woon foon  @ SG
//    * 2.  3c7fcf7267114cd4a4b7196408c45ff8  @ zhong zhou  @ Thailand
//    * 3.  d71d61ce99fb4b9aa5df37a461266188  @ xin ya
//    * 4.  a37d424f88f045af94fb87bc4a26bed8  @ ming yuan


    public static class FBInfoXml {
        // xml文件名称
        public static final String XML_NAME = "FACEBOOK_USER_INFO";
        public static final KEY<String> KEY_FACEBOOK_NAME = new KEY<String>("fb_name", "");
        public static final KEY<String> KEY_FACEBOOK_TOKEN = new KEY<String>("fb_token", "");

        public static final KEY<String> KEY_FACEBOOK_UID = new KEY<String>("fb_uid", "dd0771daf1104e828b95f18c034a6e09");

        public static final KEY<String> KEY_FACEBOOK_ICON = new KEY<String>("fb_icon", "");
        public static final KEY<String> KEY_FACEBOOK_SEX = new KEY<String>("fb_sex", "");
        public static final KEY<String> KEY_FACEBOOK_COUNTRY = new KEY<String>("fb_country", "");
        public static final KEY<Integer> KEY_FACEBOOK_GOLD_NUM = new KEY<Integer>("fb_gold_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_FOLLOWED_NUM = new KEY<Integer>("fb_followed_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_LIKE_NUM = new KEY<Integer>("fb_like_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_BOTTLE_NUM = new KEY<Integer>("fb_bottle_num", 0);
        public static final KEY<String> KEY_UID3 = new KEY<String>("uid3", "");
        //        public static final KEY<Boolean> KEY_IS_VISITOR = new KEY<Boolean>("isVisitor", false);
        public static final KEY<String> KEY_FACEBOOK_PROFESSION = new KEY<String>("fb_profession", "");
        public static final KEY<String> KEY_FACEBOOK_SLOGAN = new KEY<String>("fb_slogan", "");
        public static final KEY<Integer> KEY_VIP = new KEY<Integer>("vip", -1);
        /**
         * 存储userinfo到sharepre
         */
        public static void persistenceUserInfo(UserInfo info) {
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_UID.key, info.uid);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_NAME.key, info.name);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_ICON.key, info.icon);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_SEX.key, info.sex);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_COUNTRY.key, info.country);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_TOKEN.key, info.token);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_UID3.key, info.uidNum);

            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_GOLD_NUM.key, info.coinNum);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_FOLLOWED_NUM.key, info.followedNum);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_LIKE_NUM.key, info.likedNum);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_BOTTLE_NUM.key, info.bottleNum);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    FBInfoXml.KEY_FACEBOOK_SLOGAN.key, info.slogan);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    FBInfoXml.KEY_FACEBOOK_PROFESSION.key, info.profession);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    FBInfoXml.KEY_VIP.key, info.vip);
        }

        public static void clearUserInfo() {
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_UID.key,
                    FBInfoXml.KEY_FACEBOOK_UID.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_NAME.key,
                    FBInfoXml.KEY_FACEBOOK_NAME.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_ICON.key,
                    FBInfoXml.KEY_FACEBOOK_ICON.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_SEX.key,
                    FBInfoXml.KEY_FACEBOOK_SEX.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_COUNTRY.key,
                    FBInfoXml.KEY_FACEBOOK_COUNTRY.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_TOKEN.key,
                    FBInfoXml.KEY_FACEBOOK_TOKEN.defaultValue);
            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_UID3.key,
                    FBInfoXml.KEY_UID3.defaultValue);

            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_GOLD_NUM.key,
                    FBInfoXml.KEY_FACEBOOK_GOLD_NUM.defaultValue);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_FOLLOWED_NUM.key,
                    FBInfoXml.KEY_FACEBOOK_FOLLOWED_NUM.defaultValue);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_LIKE_NUM.key,
                    FBInfoXml.KEY_FACEBOOK_LIKE_NUM.defaultValue);
            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_BOTTLE_NUM.key,
                    FBInfoXml.KEY_FACEBOOK_BOTTLE_NUM.defaultValue);

            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_PROFESSION.key,
                    FBInfoXml.KEY_FACEBOOK_PROFESSION.defaultValue);

            SharePreferenceManager.setString(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_SLOGAN.key,
                    FBInfoXml.KEY_FACEBOOK_SLOGAN.defaultValue);

            SharePreferenceManager.setInt(GeneralFrameApplication.getInstance(), SharePreferenceManager.FBInfoXml.XML_NAME,
                    FBInfoXml.KEY_VIP.key,
                    FBInfoXml.KEY_VIP.defaultValue);
        }

    }

    public static class BGInfoXml {
        // xml文件名称
        public static final String XML_NAME = "BACKGROUND_INFO";
        public static final KEY<Integer> BACKGROUND = new KEY<Integer>("key_background", 0);
    }

    public static class LikeInfoXml {
        // xml文件名称
        public static final String XML_NAME = "LIKE_INFO";
    }

    public static class HeaderInfoXml {
        // xml文件名称
        public static final String XML_NAME = "HEADER_INFO";
        public static final KEY<Integer> KEY_HEADER_SORT = new KEY<Integer>("header_sort", 1);
    }


    //获取
    public static String getString(Context ctx, String xmlName, String key, String defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    //设置
    public static void setString(Context ctx, String xmlName, String key, String value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static Long getLong(Context ctx, String xmlName, String key, Long defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    //设置
    public static void setLong(Context ctx, String xmlName, String key, Long value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public static int getInt(Context ctx, String xmlName, String key, int defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    //设置
    public static void setInt(Context ctx, String xmlName, String key, int value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static Boolean getBoolean(Context ctx, String xmlName, String key, Boolean defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    //设置
    public static void setBoolean(Context ctx, String xmlName, String key, Boolean value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 用于设置key的名称和默认值,key值运用泛型
     */
    public static class KEY<T> {
        public final String key;// 键的名称
        public final T defaultValue;// 键的默认值

        public KEY(String key, T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }

}

