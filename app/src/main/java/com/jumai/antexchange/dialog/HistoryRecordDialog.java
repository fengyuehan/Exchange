package com.jumai.antexchange.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jumai.antexchange.Constants;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.CoinUnitBean;
import com.jumai.antexchange.ui.historyrecord.adapter.HistoryRecordItemAdapter;
import com.jumai.antexchange.view.HistoryRecordCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName HistoryRecordDialog
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordDialog extends BottomSheetDialog {

    @BindView(R.id.et_coin_type)
    EditText etCoinType;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.cb_buy)
    CheckBox cbBuy;
    @BindView(R.id.cb_sell)
    CheckBox cbSell;
    @BindView(R.id.cb_complete)
    CheckBox cbComplete;
    @BindView(R.id.cb_cancel)
    CheckBox cbCancel;

    private Context mContext;
    private HistoryRecordCallBack mHistoryRecordCallBack;
    private BottomSheetBehavior<View> mBehavior;
    private String mPriceUnitType;
    private String mType;
    private List<Integer> mPaymentState;
    private List<CheckBox> mPayStatusList;
    private List<CoinUnitBean> mData;
    private HistoryRecordItemAdapter mHistoryRecordItemAdapter;
    private int mPosition;

    public HistoryRecordDialog(@NonNull Context context, List<CoinUnitBean> mData, HistoryRecordCallBack mHistoryRecordCallBack) {
        super(context);
        this.mContext = context;
        this.mData = mData;
        this.mHistoryRecordCallBack = mHistoryRecordCallBack;
        initDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void initDialog() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_history_record, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        mBehavior = BottomSheetBehavior.from((View) contentView.getParent());
        mPaymentState = new ArrayList<>();
        mPayStatusList = new ArrayList<>();
        mPaymentState.add(Constants.EXCHANGE_BUY);
        mPaymentState.add(Constants.EXCHANGE_SELL);
        mPaymentState.add(Constants.EXCHANGE_COMPLETE);
        mPaymentState.add(Constants.EXCHANGE_CANCEL);
        mPayStatusList.add(cbBuy);
        mPayStatusList.add(cbSell);
        mPayStatusList.add(cbComplete);
        mPayStatusList.add(cbCancel);
        mType = "";
        mPriceUnitType = "";
        mPosition = -1;
        initRecyclerView();
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mHistoryRecordItemAdapter = new HistoryRecordItemAdapter(mData);
        rv.setAdapter(mHistoryRecordItemAdapter);
        mHistoryRecordItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CoinUnitBean> mList = adapter.getData();
                Log.e("zzf", "mPosition1 = " + mPosition + ";" + "position1 = " + position);
                if (mPosition != position) {
                    if (mPosition != -1) {
                        adapter.getViewByPosition(rv, mPosition, R.id.tv_coin_price_unit).setSelected(false);
                        adapter.getViewByPosition(rv, position, R.id.tv_coin_price_unit).setSelected(true);
                        mPriceUnitType = mList.get(position).getCoinPriceUnit();
                    } else {
                        adapter.getViewByPosition(rv, position, R.id.tv_coin_price_unit).setSelected(true);
                        mPriceUnitType = mList.get(position).getCoinPriceUnit();
                    }
                    mPosition = position;
                } else {
                    adapter.getViewByPosition(rv, position, R.id.tv_coin_price_unit).setSelected(false);
                    mPriceUnitType = "";
                    mPosition = position;
                }
            }
        });
    }


    @OnClick({R.id.tv_reset, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset:
                for (int i = 0; i < mPayStatusList.size(); i++) {
                    mPayStatusList.get(i).setChecked(false);
                }
                mType = "";
                etCoinType.setText("");
                mPriceUnitType = "";
                if (mPosition != -1) {
                    mHistoryRecordItemAdapter.getViewByPosition(rv, mPosition, R.id.tv_coin_price_unit).setSelected(false);
                    mPosition = -1;
                }
                break;
            case R.id.tv_sure:
                String str = "";
                for (int i = 0; i < mPayStatusList.size(); i++) {
                    if (mPayStatusList.get(i).isChecked()) {
                        str += String.valueOf(mPaymentState.get(i));
                    }
                }
                mType = str;
                String mCoinType = etCoinType.getText().toString().trim();
                if (mCoinType == null || TextUtils.isEmpty(mCoinType)) {
                    Toast.makeText(mContext, "请输入币种", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mPriceUnitType)) {
                    Toast.makeText(mContext, "请选择计价单位", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("zzf", "mCoinType = " + mCoinType + ";" + "mPriceUnitType = " + mPriceUnitType + ";" + "mType = " + mType);
                mHistoryRecordCallBack.callBack(mCoinType, mPriceUnitType, mType);
                dismiss();
                break;
        }
    }
}
