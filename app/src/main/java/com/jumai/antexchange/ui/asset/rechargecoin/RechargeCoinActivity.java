package com.jumai.antexchange.ui.asset.rechargecoin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.client.result.ParsedResultType;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.asset.coinlist.CoinListActivity;
import com.jumai.antexchange.utils.ClipboardUtils;
import com.jumai.antexchange.utils.ImageUtil;
import com.mylhyl.zxing.scanner.encode.QREncode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName RechargeCoinActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public class RechargeCoinActivity extends BaseActivity<RechargeCoinView, RechargeCoinPresenter> implements RechargeCoinView {
    @BindView(R.id.iv_coin_image)
    ImageView ivCoinImage;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_copy_address)
    TextView tvCopyAddress;
    @BindView(R.id.tv_memo)
    TextView tvMemo;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;

    private String mAddress;
    private Bitmap mBitmap;
    private String mCoinType;
    private String mUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin_charging;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mCoinType = getIntent().getStringExtra("name");
        mUrl = getIntent().getStringExtra("url");
        if (mCoinType != null && mUrl != null){
            tvCoinName.setText(mCoinType);
            ImageUtil.load(this, mUrl, ivCoinImage);
        }
        tvCopyAddress.setSelected(true);
        mAddress = tvAddress.getText().toString().trim();
        generateQRCode();
    }

    private void generateQRCode() {
        mBitmap = new QREncode.Builder(this)
                .setColor(getResources().getColor(R.color.color_33))//二维码颜色
                .setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                .setContents(mAddress)//二维码内容
                .setSize(1500)
                .build().encodeAsBitmap();
        ivQrCode.setImageBitmap(mBitmap);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.ll_select_coin, R.id.tv_copy_address, R.id.tv_copy_memo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_select_coin:
                startActivity(new Intent(RechargeCoinActivity.this, CoinListActivity.class));
                break;
            case R.id.tv_copy_address:
                tvCopyAddress.setSelected(false);
                ClipboardUtils.copyText(tvCopyAddress.getText().toString().trim());
                ToastUtils.showShort(R.string.common_copy_succ);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvCopyAddress.setSelected(true);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.tv_copy_memo:
                ClipboardUtils.copyText(tvMemo.getText().toString().trim());
                ToastUtils.showShort(R.string.common_copy_succ);
                break;
        }
    }
}
