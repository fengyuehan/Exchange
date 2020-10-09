package com.jumai.antexchange.ui.asset.legalcoindetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseRefreshActivity;
import com.jumai.antexchange.bean.CoinDetailBean;
import com.jumai.antexchange.dialog.BottomListDialog;
import com.jumai.antexchange.ui.asset.adapter.LegalCoinDetailAdapter;
import com.jumai.antexchange.ui.asset.coindetail.CoinDetailActivity;
import com.jumai.antexchange.ui.asset.coindetail.CoinDetailView;
import com.jumai.antexchange.ui.asset.detail.SingleDetailAcivity;
import com.jumai.antexchange.ui.asset.rechargecoin.RechargeCoinActivity;
import com.jumai.antexchange.ui.asset.transfer.TransferCoinActivity;
import com.jumai.antexchange.utils.ImageUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName CoinDetailActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class LegalCoinDetailActivity extends BaseRefreshActivity<CoinDetailBean, LegalCoinDetailView, LegalCoinDetailPresenter> implements CoinDetailView, BottomListDialog.CheckedListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_coin_img)
    ImageView ivCoinImg;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.iv_coin_type_img)
    ImageView ivCoinTypeImg;
    @BindView(R.id.tv_cion_type)
    TextView tvCionType;
    @BindView(R.id.tv_available)
    TextView tvAvailable;
    @BindView(R.id.tv_frozen)
    TextView tvFrozen;
    @BindView(R.id.tv_conversion)
    TextView tvConversion;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private String mUrl;
    private String mCoinName;
    private String mAvailableCoin;
    private String mFrozenCoin;
    private String mConversionCoin;
    private LegalCoinDetailAdapter mLegalCoinDetailAdapter;
    private List<CoinDetailBean> mList;
    private List<String> mData;
    private BottomListDialog mBottomListDialog;
    private String[] mDialogTitle = {"全部", "提币", "充币", "转出", "转入"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_legal_coin_assert_detail;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mUrl = getIntent().getStringExtra("mUrl");
        mCoinName = getIntent().getStringExtra("coinName");
        mAvailableCoin = getIntent().getStringExtra("available");
        mFrozenCoin = getIntent().getStringExtra("frozen");
        mConversionCoin = getIntent().getStringExtra("conversion");
        if (mAvailableCoin != null && mFrozenCoin != null && mConversionCoin != null){
            tvAvailable.setText(mAvailableCoin);
            tvFrozen.setText(mFrozenCoin);
            tvConversion.setText(mConversionCoin);
        }
        if (mCoinName != null) {
            tvCoinName.setText(mCoinName);
            tvCionType.setText(mCoinName);
        }
        if (mUrl != null) {
            ImageUtil.load(this, mUrl, ivCoinImg);
            ImageUtil.load(this, mUrl, ivCoinTypeImg);
        }
        llHeader.setVisibility(View.GONE);
        initRecyclerView();
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        mLegalCoinDetailAdapter = new LegalCoinDetailAdapter(mList);
        rv.setAdapter(mLegalCoinDetailAdapter);
        mLegalCoinDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinDetailBean coinDetailBean = (CoinDetailBean) adapter.getData().get(position);
                Intent intent = new Intent(LegalCoinDetailActivity.this, SingleDetailAcivity.class);
                intent.putExtra("name", coinDetailBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 200) {
                    llHeader.setVisibility(View.VISIBLE);
                } else {
                    llHeader.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mData = Arrays.asList(mDialogTitle);
    }

    @OnClick({R.id.iv_back, R.id.iv_select,R.id.ll_recharge_coin, R.id.ll_pick_up_coin, R.id.ll_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                if (mBottomListDialog == null) {
                    mBottomListDialog = new BottomListDialog(this);
                    mBottomListDialog.setData(0, mData);
                }
                mBottomListDialog.show();
                break;
            case R.id.ll_recharge_coin:
                //跳转充币界面
                Intent intent = new Intent(LegalCoinDetailActivity.this, RechargeCoinActivity.class);
                intent.putExtra("name",mCoinName);
                intent.putExtra("url",mUrl);
                startActivity(intent);
                break;
            case R.id.ll_pick_up_coin:
                //跳转提币界面

                break;
            case R.id.ll_transfer:
                //跳转划转界面
                Intent intent1 = new Intent(LegalCoinDetailActivity.this, TransferCoinActivity.class);
                intent1.putExtra("coinName",mCoinName);
                intent1.putExtra("amount",mAvailableCoin);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void checked(int position, String info) {

    }

    @Override
    protected SmartRefreshLayout setRefreshLayout() {
        return srl;
    }

    @Override
    protected BaseQuickAdapter setRefreshAdapter() {
        return mLegalCoinDetailAdapter;
    }
}
