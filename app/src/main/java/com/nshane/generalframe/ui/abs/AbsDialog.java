package com.nshane.generalframe.ui.abs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.nshane.generalframe.R;
import com.nshane.generalframe.utils.Constants;
import com.nshane.generalframe.utils.LogUtil;


/**
 * Created by bryan on 2017-12-28.
 */

public abstract class AbsDialog extends ProgressDialog {


    public AbsDialog(Context context) {
        super(context, R.style.AppCompatDialog);
    }

    public AbsDialog(Context context, int theme) {
        super(context, theme);
    }


    // find res id @ layout of separated custom dialog
    public void setUpView() {

    }

    public abstract int getLayoutId();


    private void initDialog() {
        // dialog cancelable
        setCancelable(true);
        //设置可取消与否，点击其他区域取消
        //setCanceledOnTouchOutside(false);
        setContentView(getLayoutId());
        setUpView();

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(Constants.TAG, "AbsDialog 调用");
        initDialog();
    }

    // 设置点击非dialog区域取消
    public void setCanceledOutside(boolean b) {
        setCanceledOnTouchOutside(b);
    }


    @Override
    public void show() {
        super.show();
    }
}
