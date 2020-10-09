package com.jumai.antexchange.ui.ipSetting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.AntApplication;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.config.Api;
import com.jumai.antexchange.db.DataManage;
import com.jumai.antexchange.utils.net.util.RxUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName IpSettingActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/19
 * @Version 1.0
 */
public class IpSettingActivity extends BaseActivity<IpSettingView, IpSettingPresenter> implements IBaseView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_address1)
    TextView tvAddress1;
    @BindView(R.id.tv_address2)
    TextView tvAddress2;
    @BindView(R.id.tv_address3)
    TextView tvAddress3;
    @BindView(R.id.et_ip)
    EditText etIp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ip_setting;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText("IP设置");
        etIp.setText(Api.HTTP_URL);
        tvAddress1.setText(Api.WALLET_DEV_BASE_URL);
        tvAddress2.setText(Api.WALLET_TEST_BASE_URL);
        tvAddress3.setText(Api.WALLET_CLOUD_BASE_URL);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.bt_restart1, R.id.bt_restart2, R.id.bt_restart3, R.id.bt_restart4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_restart1:
                setIpAndReset(Api.WALLET_DEV_BASE_URL);
                break;
            case R.id.bt_restart2:
                setIpAndReset(Api.WALLET_TEST_BASE_URL);
                break;
            case R.id.bt_restart3:
                setIpAndReset(Api.WALLET_CLOUD_BASE_URL);
                break;
            case R.id.bt_restart4:
                setIp();
                break;
        }
    }

    private void setIp() {
        String ip = etIp.getText().toString().trim();
        if (TextUtils.isEmpty(ip)) {
            Toast.makeText(this, R.string.tips_ip_input, Toast.LENGTH_SHORT).show();
            return;
        }
        setIpAndReset(ip);
    }

    private void setIpAndReset(String ip) {
        DataManage.setIp(ip);
        RxUtil.delay(() -> {
            restartApp();
        }, 2000);
    }

    private void restartApp() {
        Intent intent = AntApplication.getInstance().getPackageManager().getLaunchIntentForPackage(AntApplication.getInstance().getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(AntApplication.getInstance(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) AntApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis(), restartIntent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
