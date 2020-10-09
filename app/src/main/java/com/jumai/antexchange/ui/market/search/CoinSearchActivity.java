package com.jumai.antexchange.ui.market.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.HotCoinBean;
import com.jumai.antexchange.bean.SearchHistoryBean;
import com.jumai.antexchange.db.DataManage;
import com.jumai.antexchange.ui.market.adapter.CoinSearchAdapter;
import com.jumai.antexchange.utils.TextWatchListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName CoinSearchActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class CoinSearchActivity extends BaseActivity<CoinSearchView, CoinSearchPresenter> implements CoinSearchView, CoinSearchAdapter.CoinCollectionCallBack {
    @BindView(R.id.et_seach_coin)
    EditText etSeachCoin;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tfl)
    TagFlowLayout tfl;
    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;
    @BindView(R.id.tfl2)
    TagFlowLayout tfl2;
    @BindView(R.id.ll_hot_coin_name)
    LinearLayout llHotCoinName;
    @BindView(R.id.rv)
    RecyclerView rv;


    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld"};
    private List<HotCoinBean> mList;
    private List<String> mData;
    private CoinSearchAdapter mCoinSearchAdapter;
    private String[] str;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initTagFlow();
    }

    private void initTagFlow() {
        tfl.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(CoinSearchActivity.this).inflate(R.layout.item_hot_coin, tfl, false);
                tv.setText(s);
                return tv;
            }
        });
        tfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String coinName  = mVals[position];
                etSeachCoin.setText(coinName);
                //saveSearchHistory(coinName);
                //请求数据
                //mPresenter
                initRecyclerView();
                return true;
            }
        });
        mData = getSearchHistory();
        str = new String[mData.size()];
        mData.toArray(str);
        tfl2.setAdapter(new TagAdapter<String>(str) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(CoinSearchActivity.this).inflate(R.layout.item_search_history, tfl2, false);
                tv.setText(s);
                return tv;
            }
        });

        tfl2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String coinName  = mVals[position];
                etSeachCoin.setText(coinName);
                //saveSearchHistory(coinName);
                //请求数据
                //mPresenter
                initRecyclerView();
                return true;
            }
        });
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(CoinSearchActivity.this));
        mCoinSearchAdapter = new CoinSearchAdapter(mList);
        rv.setAdapter(mCoinSearchAdapter);
        mCoinSearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HotCoinBean hotCoinBean = (HotCoinBean) adapter.getData().get(position);
                saveSearchHistory(hotCoinBean.getName());
                //跳转到K线图

            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        etSeachCoin.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0){
                    llHotCoinName.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    //saveSearchHistory(s.toString());
                    //请求数据
                    //mPresenter
                    initRecyclerView();
                }else {
                    llHotCoinName.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_cancel, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cancel:
                DataManage.clearHistoryRecord();
                mData.clear();
                str = new String[mData.size()];
                mData.toArray(str);
                tfl2.setAdapter(new TagAdapter<String>(str) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) LayoutInflater.from(CoinSearchActivity.this).inflate(R.layout.item_search_history, tfl2, false);
                        tv.setText(s);
                        return tv;
                    }
                });
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    @Override
    public void getCollectionData(List<HotCoinBean> data) {
        if (data != null){
            mList = data;
        }
    }


    @Override
    public void callBack(String coinName) {
        //请求接口
        //mPresenter
    }

    private void saveSearchHistory(String str){
        String coinName = DataManage.getCoinName();
        String[] historyStr = coinName.split("&");
        List<String> historyList = new ArrayList<String>(Arrays.asList(historyStr));
        if (historyList.size() > 0){
            for (int i = 0; i < historyList.size(); i++){
                if (str.equals(historyList.get(i))){
                    historyList.remove(i);
                    break;
                }
            }
            //把最新输入的数据放在最前面
            historyList.add(0,str);
            //sp里最多存8条数据
            if (historyList.size() > 8){
                historyList.remove(historyList.size() -1);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i < historyList.size(); i++){
                sb.append(historyList.get(i))
                        .append("&");
            }
            DataManage.setCoinName(sb.toString());
        }else {
            DataManage.setCoinName(str);
        }
    }

    private List<String> getSearchHistory(){
        String[] historyStr =  DataManage.getCoinName().split("&");
        List<String> historyList = new ArrayList<String>(Arrays.asList(historyStr));
        if (historyList.size() == 1 && historyList.get(0).equals("")) { //如果没有搜索记录，split之后第0位是个空串的情况下
            historyList.clear();  //清空集合，这个很关键
        }
        return historyList;
    }

}
