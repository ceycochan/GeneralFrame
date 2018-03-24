package com.nshane.generalframe.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.nshane.generalframe.R;
import com.nshane.generalframe.interfaces.IPresenter;
import com.nshane.generalframe.ui.abs.BaseActivity;
import com.nshane.generalframe.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bryan on 2018-3-5.
 */

public class UpdateActivity extends BaseActivity {


    @BindView(R.id.tv_version)
    TextView tvVersion;

    private int onlineVersionCode = 16;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UpdateActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initCurrentVersion();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_related;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }


    private void initCurrentVersion() {

        int version= Utils.getVersionCode(this);

        tvVersion.setText(String.format(getString(R.string.tip_version),version));

    }


}
