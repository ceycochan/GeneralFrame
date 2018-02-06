package com.nshane.generalframe.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nshane.generalframe.R;
import com.nshane.generalframe.http.MyTask;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.AbsFragment;
import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.FileUtil;
import com.nshane.generalframe.utils.LogUtil;
import com.nshane.generalframe.utils.SharePreferenceManager;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lbl on 2017-6-26.
 */

public class SettingFragment extends AbsFragment {


    @BindView(R.id.ll_user_update)
    LinearLayout llUserUpdate;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;


    private String gpPkg = "com.android.vending";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected int getLayoutResId() {  // USELESS FOUND ON 0630
        return R.layout.fragment_set;
    }


    @Override
    public void onResume() {
        super.onResume();


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


    private void setUserInfo(String userName, String icon) {

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


    private void goStore(String appPkg, String marketPkg) {
        if (TextUtils.isEmpty(appPkg)) return;

        // 跳转指定商城
        Intent intentSet = new Intent(Intent.ACTION_VIEW);
        intentSet.setData(Uri.parse("market://details?id=" + appPkg));
        /**
         * 在不指定setPackage的情况下,系统过滤出实现 "market://details?id=" 接口的商店类应用已供用户选择
         */
        intentSet.setPackage(marketPkg);
        intentSet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intentSet.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intentSet);
        } else {
            // 设备没有GP,使用浏览器跳转
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPkg));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                LogUtil.d("cg", "设备包含浏览器");
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "store & browser are not available", Toast.LENGTH_SHORT).show();
            }
        }


        /**
         * 多层判断跳转store
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + appPkg));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            LogUtil.d("cg", "筛选浏览器商城app");
            startActivity(intent);
        } else {
            LogUtil.d("cg","浏览器跳转GP");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPkg));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                LogUtil.d("cg","设备包含浏览器");
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "store & browser are not available", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @OnClick({R.id.ll_user_update, R.id.ll_user_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_update:
                Toast.makeText(getActivity(), "Update is clicked", Toast.LENGTH_SHORT).show();
                // TODO: 2018-1-30 使用sea bottle指向GP测试
                goStore("co.meetu", gpPkg);
                break;
            case R.id.ll_user_info:
                break;
        }
    }
}
