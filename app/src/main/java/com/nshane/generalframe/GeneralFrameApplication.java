package com.nshane.generalframe;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.utils.SharePreferenceManager;

import java.util.Iterator;
import java.util.List;


public class GeneralFrameApplication extends Application {
    private static GeneralFrameApplication mInstance;

    public static Context context;


    private String userFBId;

    public static GeneralFrameApplication getInstance() {
        return mInstance;
    }

    public GeneralFrameApplication() {
        mInstance = this;
//        MultiDex.install(this);
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //init demo helperappContext = this;
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        LogUtil.d("dax", "进程名:" + processAppName);
        userFBId = SharePreferenceManager.getString(context,
                SharePreferenceManager.FBInfoXml.XML_NAME,
                SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_UID.key, SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_UID.defaultValue);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    /**
     * MultiDex: the way to solve 65k(65535) limitation:
     * <p>
     * fyi-1: 65k is the sum total of methods invoked by a DEX
     * <p>
     * fyi-2: 1. 为了解决这个限制，可以使用multidex支持库，成为您的应用程序的主DEX文件的一部分，然后设法获得额外的DEX文件和它们所包含的代码。
     * 2. 如果您的项目配置为multidex用的minSdkVersion20或更低，并且部署到目标运行Android4.4（API级别20）或更低的设备，Android Studio中禁用即时运行(Instant Run)
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}





       
