package com.jumai.antexchange.ui.verification;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.passwordsetting.PassWordSettingActivity;
import com.jumai.antexchange.ui.resetpassword.ResetPasswordActivity;
import com.jumai.antexchange.view.VerificationCodeView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName SafeVerificationActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class SafeVerificationActivity extends BaseActivity<SafeVerificationView, SafeVerificationPresenter> implements SafeVerificationView, VerificationCodeView.OnCodeFinishListener {

    @BindView(R.id.vcv)
    VerificationCodeView vcv;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_time)
    LinearLayout llTime;

    private String mPhone;
    private Timer mTimer = new Timer();
    private int mCount = 60;
    private String mVerificationCode;
    private String mType;//1：设置密码2：忘记密码

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safe_verification;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra("phone");
        mType = getIntent().getStringExtra("type");
        getTimer();
        if (mPhone != null) {
            tvTips.setText("已向" + mPhone + "发送验证码");
        }

    }

    private void getTimer() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        llTime.setClickable(false);
                        tvContent.setSelected(false);
                        tvTime.setText(getString(R.string.tips_get_code_again,mCount));
                        mCount--;
                        if(mCount == 0){
                            resendCode();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void resendCode() {
        mTimer.cancel();
        mTimer = new Timer();
        llTime.setClickable(true);
        tvContent.setSelected(true);
        tvTime.setVisibility(View.GONE);
        mCount = 60;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_close, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_content:
                tvTime.setVisibility(View.VISIBLE);
                getTimer();
                if(tvTips.getVisibility() == View.VISIBLE){
                    tvTips.setVisibility(View.GONE);
                }
                break;
        }
    }


    @Override
    public void onTextChange(View view, String content) {
        mVerificationCode = content;
    }

    @Override
    public void onComplete(View view, String content) {
        mPresenter.verification(mVerificationCode);
    }

    @Override
    public void verificationSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if(mType != null){
            if ("1".equals(mType)){
                startActivity(new Intent(SafeVerificationActivity.this, PassWordSettingActivity.class).putExtra("phone",mPhone).putExtra("code",mVerificationCode));
            }else {
                startActivity(new Intent(SafeVerificationActivity.this, ResetPasswordActivity.class).putExtra("phone",mPhone).putExtra("code",mVerificationCode));
            }
        }

    }

    @Override
    public void onError() {
        tvTips.setVisibility(View.VISIBLE);
    }
}
