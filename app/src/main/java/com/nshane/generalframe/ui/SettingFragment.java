package com.nshane.generalframe.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshane.generalframe.R;
import com.nshane.generalframe.http.MyTask;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;
import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.FileUtil;
import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.utils.SharePreferenceManager;

import java.io.File;


/**
 * Created by lbl on 2017-6-26.
 */

public class SettingFragment extends AbsFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container);
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }


    @Override
    protected int getLayoutResId() {  // USELESS FOUND ON 0630
        return R.layout.fragment_set;
    }


    @Override
    public void onResume() {
        super.onResume();

//        mUserPresenter.getCurUserInfo(userId); // 从个人信息页面返回后再次请求数据
        LogUtil.d("lzz-refresh", "settings onResume");


        LogUtil.d("cg", "settings页面 onResume");


    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {
    }

    @Override
    public void initView() {

    }

    private Handler mHandler = new Handler();

    private void shareApp() {
        /**
         * 1. 设备有google play
         */

        /**
         * if here used at fragment,declare activity judgement
         * such as
         * final Activity activity = getActivity();
         if (activity == null) {
         return;
         }
         */
        final String picFileName = "share_funny.png";
        final String shareTo = getString(R.string.share);
        File dirFile = new File(Constants.IMAGE_PATH);
        if (!dirFile.exists()) { // if direFile is not exists condition
            dirFile.mkdir();  //Creates the directory named by this abstract pathname.
        }

        final String sharePicPath = Constants.IMAGE_PATH + picFileName;
        final String description = "http://www.meetu.co";
        //multi-thread opt

        /**
         * 分享功能暂时屏蔽
         */
        Runnable runSend = new Runnable() {
            @Override
            public void run() {
                File f = FileUtil.write2SDFromInput(Constants.IMAGE_PATH, picFileName,
                        FileUtil.getInputStreamFromAsset(getActivity().getApplicationContext(), "material/pics/app_funny.png"));
                if (f != null && f.exists()) {
                    //由文件获取uri
                    //考虑文件是否存在

                    /**
                     * 只发送产品主页的分享
                     */
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, description);  // 如果无法找到图片,以文本方式输出
                            intent.setType("text/plain");
                            //设置分享列表的标题,并且每次都显示分享列表
                            startActivity(Intent.createChooser(intent, shareTo));
                        }
                    });

                }
            }
        };

        if (FileUtil.isFileExist(sharePicPath)) {
            if (new File(sharePicPath).length() < 100) {
                new File(sharePicPath).delete();
                MyTask.runInBackground(runSend, true);
                return;
            }
            Uri imageUri = Uri.fromFile(new File(sharePicPath));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, description);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, shareTo));
            return;
        }
        MyTask.runInBackground(runSend, false);
    }


    private void setUserInfo(String userName, String icon) { //填充数据

    }


    private void initUserInfo() {

        String userName = SharePreferenceManager.getString(getActivity(),
                SharePreferenceManager.FBInfoXml.XML_NAME,
                SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_NAME.key,
                SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_NAME.defaultValue);

        String icon = SharePreferenceManager.getString(getActivity(),
                SharePreferenceManager.FBInfoXml.XML_NAME,
                SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_ICON.key,
                SharePreferenceManager.FBInfoXml.KEY_FACEBOOK_ICON.defaultValue);


        setUserInfo(userName, icon);
    }


}
