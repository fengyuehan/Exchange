package com.jumai.antexchange.ui.kyc.countrycertification;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.BottomListDialog;
import com.jumai.antexchange.ui.areacode.AreaCodeActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName CountryCertificationActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class CountryCertificationActivity extends BaseActivity<CountryCertificationView, CountryCertificationPresenter> implements CountryCertificationView {
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.et_idcard_code)
    EditText etIdcardCode;
    @BindView(R.id.view_name)
    View viewName;
    @BindView(R.id.view_id_card)
    View viewIdCard;

    private String[] mData = {"身份证", "护照"};
    private BottomListDialog mBottomListDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kyc_country_certification;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        viewName.setSelected(false);
        viewIdCard.setSelected(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    viewName.setSelected(true);
                } else {
                    viewName.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etIdcardCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    viewIdCard.setSelected(true);
                } else {
                    viewIdCard.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_go_back, R.id.iv_jump, R.id.ll_country, R.id.ll_id_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.iv_jump:
                String name = etName.getText().toString().trim();
                String idCardCode = etIdcardCode.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(CountryCertificationActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(idCardCode)){
                    Toast.makeText(CountryCertificationActivity.this,"请输入身份证号或护照号",Toast.LENGTH_SHORT).show();
                    return;
                }
                //请求接口

                break;
            case R.id.ll_id_card:
                if (mBottomListDialog == null) {
                    mBottomListDialog = new BottomListDialog(CountryCertificationActivity.this);
                    mBottomListDialog.setData(0, Arrays.asList(mData));
                    mBottomListDialog.setCheckedListener(new BottomListDialog.CheckedListener() {
                        @Override
                        public void checked(int position, String info) {
                            if (info != null) {
                                tvIdCard.setText(info);
                            }
                        }
                    });
                }
                mBottomListDialog.show();
                break;
            case R.id.ll_country:
                startActivity(new Intent(CountryCertificationActivity.this, AreaCodeActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                String country = data.getStringExtra(AreaCodeActivity.COUNTRY);
                if (country != null) {
                    tvCountry.setText(country);
                }
            }
        }
    }
}
