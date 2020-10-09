package com.jumai.antexchange.ui.asset.coinlist;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.HotCoinBean;
import com.jumai.antexchange.ui.asset.coinlist.adapter.CoinListAdapter;
import com.jumai.antexchange.ui.asset.rechargecoin.RechargeCoinActivity;
import com.jumai.antexchange.utils.TextWatchListener;
import com.jumai.antexchange.view.CoinListHeadDecoration;
import com.jumai.antexchange.view.CommonUtil;
import com.jumai.antexchange.view.LetterSlideBar;
import com.jumai.antexchange.view.SlideBarTouchListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName CoinListActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public class CoinListActivity extends BaseActivity<CoinListView, CoinListPresenter> implements CoinListView {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tfl)
    TagFlowLayout tfl;
    @BindView(R.id.rv_coin_type)
    RecyclerView rvCoinType;
    @BindView(R.id.lsv)
    LetterSlideBar lsv;

    private String[] mVals = new String[]
            {"BTC", "ETH", "HT ", "BNB", "ETHY", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld"};
    private CoinListAdapter mCoinListAdapter;
    private List<HotCoinBean> mList;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin_list;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mPresenter.getCoinListData();
        mList = new ArrayList<>();
        for (int i = 0; i < mVals.length; i++){
            HotCoinBean hotCoinBean = new HotCoinBean();
            hotCoinBean.setHotCoinBean(mVals[i]);
            mList.add(hotCoinBean);
        }
        initTagFlow();
        initRecyclerView();
    }

    @Override
    protected void initListener() {
        super.initListener();
        etSearch.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                if (!TextUtils.isEmpty(text)){
                    mPresenter.getSearchCoinListData(text);
                }
            }
        });
        lsv.setOnSideBarTouchListener(new SlideBarTouchListener() {
            @Override
            public void onTouch(String letter, boolean isTouch) {
                if (!isTouch) {
                    return;
                }
                for (int i = 0; i < mList.size(); i++) {
                    if (letter.equals(String.valueOf(mList.get(i).getName().charAt(0)))) {
                        mLinearLayoutManager.scrollToPositionWithOffset(i, -CommonUtil.dp2px(CoinListActivity.this, 10));
                        break;
                    }
                }
            }
        });
        mCoinListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HotCoinBean hotCoinBean = (HotCoinBean) adapter.getData().get(position);
                Intent intent = new Intent(CoinListActivity.this, RechargeCoinActivity.class);
                intent.putExtra("name",hotCoinBean.getName());
                intent.putExtra("url",hotCoinBean.getUrl());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void initRecyclerView() {
        Collections.sort(mList, new Comparator<HotCoinBean>() {
            @Override
            public int compare(HotCoinBean hotCoinBean, HotCoinBean t1) {
                int flag = hotCoinBean.getName_first_letter().compareTo(t1.getName_first_letter());
                return flag;
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this);
        rvCoinType.setLayoutManager(mLinearLayoutManager);
        mCoinListAdapter = new CoinListAdapter(mList);
        rvCoinType.setAdapter(mCoinListAdapter);
    }

    private void initTagFlow() {
        tfl.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(CoinListActivity.this).inflate(R.layout.item_hot_coin, tfl, false);
                tv.setText(s);
                return tv;
            }
        });
        tfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String coinName = mVals[position];
                etSearch.setText(coinName);
                mPresenter.getSearchCoinListData(coinName);
                return true;
            }
        });
    }

    @Override
    public void onError(String message) {
        Toast.makeText(CoinListActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCoinListSuccess(List<HotCoinBean> list) {
        if (list != null){
            mList = list;
            Collections.sort(mList, new Comparator<HotCoinBean>() {
                @Override
                public int compare(HotCoinBean hotCoinBean, HotCoinBean t1) {
                    int flag = hotCoinBean.getName_first_letter().compareTo(t1.getName_first_letter());
                    return flag;
                }
            });
        }
    }

    @Override
    public void getSearchListDataSuccess(List<HotCoinBean> list) {
        if (list != null){
            if (list.size() == 0){
                mCoinListAdapter.setEmptyView(getEmptyView(R.string.tip_empty));
            }else {
                mList.clear();
                mList.addAll(list);
            }
            mCoinListAdapter.notifyDataSetChanged();
        }
    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                if (!TextUtils.isEmpty(etSearch.getText().toString().trim())){
                    etSearch.setText("");
                }else {
                    finish();
                }
                break;
        }
    }
}
