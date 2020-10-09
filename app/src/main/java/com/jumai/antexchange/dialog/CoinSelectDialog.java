package com.jumai.antexchange.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class CoinSelectDialog extends BottomSheetDialog {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    private BaseActivity mContext;
    private CoinSelectListener mCoinSelectListener;
    private int mSelectPosition;
    private List<String> mAvailableTxCoinList;

    public CoinSelectDialog(@NonNull Context context, CoinSelectListener coinSelectListener) {
        super(context, R.style.CustomDialog);
        mContext = (BaseActivity) context;
        mCoinSelectListener = coinSelectListener;
        initDialog();
    }

    private void initDialog() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_coin, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        mAvailableTxCoinList = new ArrayList<>();
        //底部弹出动画
        getWindow().setWindowAnimations(R.style.BottomInOut);
        initRecyclerView();
        initListener();
        getAvailableTxCoinList();
    }

    /**
     * 获取可交易的币种列表
     */
    private void getAvailableTxCoinList() {
        mAvailableTxCoinList.add("自选");
        initTabLayout();
    }

    private void initRecyclerView() {

    }

    private void initListener() {
        etSearch.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSelectPosition = tab.getPosition();
                if (mSelectPosition == 0) {
                    getOptionalData();
                } else {
                    getCustomData();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTabLayout() {
        for (int i = 0; i < mAvailableTxCoinList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(mAvailableTxCoinList.get(i)), i, i == 0);
        }
    }

    /**
     * 获取自选数据
     */
    private void getOptionalData() {

    }

    /**
     * 获取非自选交易对数据
     */
    private void getCustomData() {

    }

    public interface CoinSelectListener {
        void select(String coinFrom, String coinTo, String price);
    }
}
