package com.jumai.antexchange.ui.asset;

import android.annotation.SuppressLint;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.Constants;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseRefreshFragment;
import com.jumai.antexchange.bean.AssetDetailBean;
import com.jumai.antexchange.ui.asset.adapter.AccountsAdapter;
import com.jumai.antexchange.ui.asset.adapter.AssetDetailAdapter;
import com.jumai.antexchange.utils.TextWatchListener;
import com.jumai.antexchange.view.CommonUtil;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class AssetFragment extends BaseRefreshFragment<AssetDetailBean, AssetView, AssetPresenter> implements AssetView {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.cb_coin_show_hide)
    CheckBox cbCoinShowHide;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.rb_coin_account)
    RadioButton rbCoinAccount;
    @BindView(R.id.rb_legal_coin_account)
    RadioButton rbLegalCoinAccount;
    @BindView(R.id.rg)
    RadioGroup rg;

    private AssetDetailAdapter mAssetDetailAdapter;
    private AccountsAdapter mAccountsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_asset;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        initViewPager();
        initRecyclerView();
    }

    private void initViewPager() {
        mAccountsAdapter = new AccountsAdapter(mActivity);
        vp.setAdapter(mAccountsAdapter);
        vp.setPageMargin(CommonUtil.dp2px(mActivity, 8));
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        mAssetDetailAdapter = new AssetDetailAdapter(new ArrayList<>());
        rv.setAdapter(mAssetDetailAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getRefreshData(true);
    }

    @Override
    public void setAssetDetailData(List<AssetDetailBean> data) {
        mAssetDetailAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                vp.setCurrentItem(checkedId == R.id.rb_coin_account ? 0 : 1, true);
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(position == 0 ? R.id.rb_coin_account : R.id.rb_legal_coin_account);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cbCoinShowHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        etSearch.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        mAccountsAdapter.setAssetShowHideListener(new AccountsAdapter.AssetShowHideListener() {
            @Override
            public void onCoinAccountShowHide(boolean var) {
                Logger.t(Constants.USER_TAG).e("币币账户:%s", var);
                //mAssetDetailAdapter.setShowDetail(var);
            }

            @Override
            public void onLegalCoinAccountShowHide(boolean var) {
                Logger.t(Constants.USER_TAG).e("法币账户:%s", var);
            }
        });

       /* mAssetDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssetDetailBean coinTypeBean = (AssetDetailBean) adapter.getData().get(position);
                if (position == 0) {//币币账户
                    Intent intent = new Intent(getActivity(), CoinDetailActivity.class);
                    intent.putExtra("mUrl", coinTypeBean.getUrl());
                    intent.putExtra("coinName", coinTypeBean.getCoinName());
                    intent.putExtra("available", coinTypeBean.getAvailable());
                    intent.putExtra("frozen", coinTypeBean.getFrozen());
                    intent.putExtra("conversion", coinTypeBean.getConversion());
                    startActivity(intent);
                } else {
                    //法币账户
                    Intent intent1 = new Intent(getActivity(), LegalCoinDetailActivity.class);
                    intent1.putExtra("mUrl", coinTypeBean.getUrl());
                    intent1.putExtra("coinName", coinTypeBean.getCoinName());
                    intent1.putExtra("available", coinTypeBean.getAvailable());
                    intent1.putExtra("frozen", coinTypeBean.getFrozen());
                    intent1.putExtra("conversion", coinTypeBean.getConversion());
                    startActivity(intent1);
                }
            }
        });*/
    }

    @Override
    protected SmartRefreshLayout setRefreshLayout() {
        return srl;
    }

    @Override
    protected BaseQuickAdapter setRefreshAdapter() {
        return mAssetDetailAdapter;
    }
}
