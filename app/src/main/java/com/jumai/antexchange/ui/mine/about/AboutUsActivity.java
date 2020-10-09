package com.jumai.antexchange.ui.mine.about;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName AboutUsActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class AboutUsActivity extends BaseActivity<AboutUsView, AboutUsPresenter> implements AboutUsView {
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private VersionCallBack mVersionCallBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvVersion.setText("版本 V" + getVersionName(this));
    }

    @Override
    protected void initData() {

    }

    private String getVersionName(Context context) {
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                if (mVersionCallBack != null){
                    mVersionCallBack.callBack(getVersionName(this));
                }
                finish();
                break;
        }
    }

    public interface VersionCallBack{
        void callBack(String version);
    }
}
