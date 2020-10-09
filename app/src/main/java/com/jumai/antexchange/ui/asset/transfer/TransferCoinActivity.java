package com.jumai.antexchange.ui.asset.transfer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.BottomListDialog;
import com.jumai.antexchange.dialog.TransferSuccessDialog;
import com.jumai.antexchange.ui.asset.transferrecord.TransferCoinRecordActivity;
import com.jumai.antexchange.ui.main.MainActivity;
import com.jumai.antexchange.view.TransferSuccessCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName TransferCoinActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferCoinActivity extends BaseActivity<TransferCoinView, TransferCoinPresenter> implements TransferCoinView, BottomListDialog.CheckedListener {
    @BindView(R.id.tv_legal_coin)
    TextView tvLegalCoin;
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    @BindView(R.id.iv_change)
    ImageView ivChange;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_coin_amount)
    TextView tvCoinAmount;

    private String mCoinType;
    private String mCoinAmount;
    private String[] mDialogTitle = {"BTC", "ETH", "USDT", "EOS", "HUSD"};
    private List<String> mData;
    private BottomListDialog mBottomListDialog;
    private TransferSuccessDialog mTransferSuccessDialog;
    private String mInfo;
    private int mPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mCoinType = getIntent().getStringExtra("coinName");
        mCoinAmount = getIntent().getStringExtra("amount");
        if (mCoinType != null) {
            tvCoinName.setText(mCoinType);
            tvCoinAmount.setText(mCoinType);
        }
        mPosition = 0;
        mInfo = tvCoin.getText().toString().trim();
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mData = Arrays.asList(mDialogTitle);
    }


    @OnClick({R.id.iv_back, R.id.iv_change, R.id.iv_jump, R.id.tv_all, R.id.tv_click, R.id.iv_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_change:
                changeLocation();
                break;
            case R.id.iv_jump:
                if (mBottomListDialog == null) {
                    mBottomListDialog = new BottomListDialog(this);
                    mBottomListDialog.setData(0, mData);
                }
                mBottomListDialog.show();
                break;
            case R.id.tv_all:
                if (mCoinAmount != null) {
                    etAmount.setText(mCoinAmount);
                }
                break;
            case R.id.tv_click:
                String mQuantity = etAmount.getText().toString().trim();
                if (Double.parseDouble(mQuantity) > Double.parseDouble(mCoinAmount)) {
                    Toast.makeText(this, "可用数量不足", Toast.LENGTH_LONG).show();
                    return;
                }
                mPresenter.transfer(mQuantity);
                break;
            case R.id.iv_select:
                Intent intent = new Intent(TransferCoinActivity.this, TransferCoinRecordActivity.class);
                intent.putExtra("coinType", tvCoinName.getText());
                startActivity(intent);
                break;
        }
    }

    private void changeLocation() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator startAnimator = null;
        ObjectAnimator endAnimator = null;
        ObjectAnimator imageAnimator = ObjectAnimator.ofFloat(ivChange, View.ROTATION, 0,180);
        if (mPosition == 0) {
            mPosition = 1;
            startAnimator = ObjectAnimator.ofFloat(tvLegalCoin, View.TRANSLATION_Y, 0, tvLegalCoin.getHeight());
            endAnimator = ObjectAnimator.ofFloat(tvCoin, View.TRANSLATION_Y, 0, -tvCoin.getHeight());
        } else {
            mPosition = 0;
            startAnimator = ObjectAnimator.ofFloat(tvLegalCoin, View.TRANSLATION_Y, tvLegalCoin.getHeight(),0);
            endAnimator = ObjectAnimator.ofFloat(tvCoin, View.TRANSLATION_Y, -tvCoin.getHeight(),0);
        }
        set.playTogether(startAnimator, endAnimator, imageAnimator);
        set.setDuration(500);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mPosition == 0){
                    mInfo = tvCoin.getText().toString();
                    Log.e("zzf","mInfo1 = " + mInfo);
                }else {
                    mInfo = tvLegalCoin.getText().toString();
                    Log.e("zzf","mInfo2 = " + mInfo);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    @Override
    public void checked(int position, String info) {
        if (info != null) {
            tvCoinName.setText(info);
            tvCoinAmount.setText(info);
        }
    }

    @Override
    public void transferSuccess() {
        if (mTransferSuccessDialog == null) {
            mTransferSuccessDialog = new TransferSuccessDialog(this, new TransferSuccessCallBack() {
                @Override
                public void callBack() {
                    startActivity(new Intent(TransferCoinActivity.this, MainActivity.class));
                }
            });
        }
        if (mInfo != null) {
            if ("法币账户".equals(mInfo)) {
                mTransferSuccessDialog.setmInfo("资金已成功划转至币币账户，马上进行币币交易");
            } else {
                mTransferSuccessDialog.setmInfo("资金已成功划转至法币账户，马上进行法币交易");
            }
        }
        mTransferSuccessDialog.show();
    }

    @Override
    public void transferFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
