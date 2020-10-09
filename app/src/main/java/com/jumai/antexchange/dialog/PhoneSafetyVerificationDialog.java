package com.jumai.antexchange.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.utils.TextWatchListener;
import com.jumai.antexchange.utils.net.util.RxUtil;
import com.jumai.antexchange.view.VertificationSuccessCallBack;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：安全验证
 */
public class PhoneSafetyVerificationDialog extends BottomSheetDialog {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_sms_code)
    EditText etSmsCode;
    @BindView(R.id.tv_get_sms_code)
    TextView tvGetSmsCode;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.et_email_code)
    EditText etEmailCode;
    @BindView(R.id.tv_get_email_code)
    TextView tvGetEmailCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    private static final int TIME = 60;
    private BaseActivity mActivity;
    private Disposable mSmsDisposable;
    private Disposable mEmailDisposable;
    private String mPhone;
    private String mEmile;
    private VertificationSuccessCallBack mVertificationSuccessCallBack;

    public PhoneSafetyVerificationDialog(@NonNull Context context,String mPhone,String mEmile,VertificationSuccessCallBack mVertificationSuccessCallBack) {
        this(context, R.style.CustomDialog);
        this.mPhone = mPhone;
        this.mEmile = mEmile;
        this.mVertificationSuccessCallBack = mVertificationSuccessCallBack;
        initView(context);
    }


    private PhoneSafetyVerificationDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    private void initView(Context context) {
        setContentView(R.layout.dialog_safety_verification);
        ButterKnife.bind(this);
        mActivity = (BaseActivity) context;
        tvGetSmsCode.setSelected(true);
        tvGetEmailCode.setSelected(true);
        initListener();
    }

    @OnClick({R.id.tv_cancel, R.id.tv_get_sms_code, R.id.tv_get_email_code, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_get_sms_code:
                getCode(true);
                break;
            case R.id.tv_get_email_code:
                getCode(false);
                break;
            case R.id.btn_next:
                String phoneCode = etSmsCode.getText().toString().trim();
                String emileCode = etEmailCode.getText().toString().trim();
                if (mVertificationSuccessCallBack != null){
                    mVertificationSuccessCallBack.callBack(phoneCode,emileCode);
                }
                dismiss();
                break;
        }
    }

    private void initListener() {
        etSmsCode.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                btnNext.setEnabled(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etEmailCode.getText()));
            }
        });

        etEmailCode.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                btnNext.setEnabled(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etSmsCode.getText()));
            }
        });
    }

    /**
     * 开始付款倒计时
     */
    private void getCode(boolean getSms) {
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .compose(RxUtil.io2main())
                .compose(RxUtil.lifecycle((RxAppCompatActivity) mActivity))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (getSms) {
                            mSmsDisposable = d;
                        } else {
                            mEmailDisposable = d;
                        }
                    }

                    @Override
                    public void onNext(Long aLong) {
                        getCodeUiChange(getSms, aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getCodeUiChange(boolean getSms, long count) {
        if (getSms) {
            tvGetSmsCode.setClickable(false);
            tvGetSmsCode.setSelected(false);
            tvGetSmsCode.setText(getContext().getString(R.string.tips_get_code_again, TIME - count));
            if (count == TIME) {
                smsDispose();
            }
        } else {
            tvGetEmailCode.setClickable(false);
            tvGetEmailCode.setSelected(false);
            tvGetEmailCode.setText(getContext().getString(R.string.tips_get_code_again, TIME - count));
            if (count == TIME) {
                emailDispose();
            }
        }
    }

    private void smsDispose() {
        if (mSmsDisposable != null) {
            mSmsDisposable.dispose();
        }
        tvGetSmsCode.setClickable(true);
        tvGetSmsCode.setSelected(true);
        tvGetSmsCode.setText("获取");
    }

    private void emailDispose() {
        if (mEmailDisposable != null) {
            mEmailDisposable.dispose();
        }
        tvGetEmailCode.setClickable(true);
        tvGetEmailCode.setSelected(true);
        tvGetEmailCode.setText("获取");
    }

    @Override
    public void dismiss() {
        smsDispose();
        emailDispose();
        super.dismiss();
    }
}
