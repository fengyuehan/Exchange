package com.jumai.antexchange.ui.asset;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.SafetyVerificationDialog;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class AddressAddActivity extends BaseActivity<AddressAddView, AddressAddPresenter> implements AddressAddView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_address)
    TextView tvTitleAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_memo)
    EditText etMemo;
    @BindView(R.id.tv_length)
    TextView tvLength;
    @BindView(R.id.btn_next)
    Button btnNext;

    private SafetyVerificationDialog mSafetyVerificationDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_add;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText("添加地址");
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        super.initListener();
        etAddress.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                btnNext.setEnabled(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etMemo.getText()));
            }
        });

        etMemo.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                String text = s.toString().trim();
                if (TextUtils.isEmpty(text)) {
                    tvLength.setText("0/16");
                } else {
                    tvLength.setText(text.length() + "/16");
                }
                btnNext.setEnabled(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(etAddress.getText()));
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_scan, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_scan:
                break;
            case R.id.btn_next:
                showSafetyVerificationDialog();
                break;
        }
    }

    private void showSafetyVerificationDialog() {
        if (mSafetyVerificationDialog == null) {
            mSafetyVerificationDialog = new SafetyVerificationDialog(this);
        }
        mSafetyVerificationDialog.show();
    }
}
