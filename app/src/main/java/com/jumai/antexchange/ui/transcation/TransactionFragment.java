package com.jumai.antexchange.ui.transcation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseRefreshFragment;
import com.jumai.antexchange.dialog.BottomListDialog;
import com.jumai.antexchange.dialog.CoinSelectDialog;
import com.jumai.antexchange.model.adapter.CommissionTxRecordsAdapter;
import com.jumai.antexchange.model.adapter.TxDataAdapter;
import com.jumai.antexchange.model.bean.CommissionTxBean;
import com.jumai.antexchange.model.bean.TxBean;
import com.jumai.antexchange.ui.historyrecord.activity.HistoryRecordActivity;
import com.jumai.antexchange.ui.widget.NumberChangeView;
import com.jumai.antexchange.utils.DataProcess;
import com.jumai.antexchange.utils.DecimalDigitsInputFilter;
import com.jumai.antexchange.utils.MathUtil;
import com.jumai.antexchange.utils.TextWatchListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class TransactionFragment extends BaseRefreshFragment<CommissionTxBean, TransactionRefreshView, TransactionPresenter> implements TransactionRefreshView {
    @BindView(R.id.iv_coin_symbol)
    ImageView ivCoinSymbol;
    @BindView(R.id.tv_symbol)
    TextView tvSymbol;
    @BindView(R.id.iv_symbol)
    ImageView ivSymbol;
    @BindView(R.id.cb_like)
    CheckBox cbLike;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    @BindView(R.id.tv_exchange_rate)
    TextView tvExchangeRate;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_cny)
    TextView tvCny;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_symbol_transaction)
    TextView tvSymbolTransaction;
    @BindView(R.id.tv_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.sb)
    SeekBar sb;
    @BindView(R.id.tv_max_balance)
    TextView tvMaxBalance;
    @BindView(R.id.tv_transaction_amount)
    TextView tvTransactionAmount;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.rv_sell)
    RecyclerView rvSell;
    @BindView(R.id.tv_latest_price)
    TextView tvLatestPrice;
    @BindView(R.id.tv_latest_cny)
    TextView tvLatestCny;
    @BindView(R.id.rv_buy)
    RecyclerView rvBuy;
    @BindView(R.id.fl_keep_num_set)
    FrameLayout flKeepNumSet;
    @BindView(R.id.fl_showing_set)
    FrameLayout flShowingSet;
    @BindView(R.id.tv_all_transaction)
    TextView tvAllTransaction;
    @BindView(R.id.rv_transaction)
    RecyclerView rvTransaction;
    @BindView(R.id.srf)
    SmartRefreshLayout srf;
    @BindView(R.id.tv_keep_num_set)
    TextView tvKeepNumSet;
    @BindView(R.id.tv_showing_set)
    TextView tvShowingSet;
    @BindView(R.id.ncv)
    NumberChangeView ncv;
    private boolean mIsBuy = true;
    private BottomListDialog mBottomListDialog;
    private List<String> mKeepNums;
    private CommissionTxRecordsAdapter mTxRecordsAdapter;
    private TxDataAdapter mTxBuyAdapter;
    private TxDataAdapter mTxSellAdapter;
    private ScaleAnimation mScaleAnimation;
    private int mShowBottomType;
    private int mSelectedNumPosition = 3;
    private int mSelectedShowPosition;
    private List<String> mShowTypeItems;
    private CoinSelectDialog mCoinSelectDialog;
    private String mSymbolFrom = "";
    private String mSymbolTo = "";
    //from余额
    private double mBalanceFrom;
    //to余额
    private double mBalanceTo;
    private int mLineCount = 1;
    private int mLineHeight = 47;
    private boolean mGetAllBuyData;
    private boolean mGetAllSellData;
    private TxBean mLastTxBean;
    private BigDecimal mMaxBuyAmount = BigDecimal.ZERO;
    private BigDecimal mMaxSellAmount = BigDecimal.ZERO;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transaction2;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvBuy.setSelected(true);
        etPrice.setText("9969.23");
        mKeepNums = Arrays.asList("1", "2", "3", "4");
        mShowTypeItems = Arrays.asList(getResources().getStringArray(R.array.tx_show_type));
        //最多输入4位小数
        InputFilter[] inputFilters = {new DecimalDigitsInputFilter(4)};
        etAmount.setFilters(inputFilters);
        etPrice.setFilters(inputFilters);

        rvTransaction.setLayoutManager(new LinearLayoutManager(mActivity));
        mTxRecordsAdapter = new CommissionTxRecordsAdapter(new ArrayList<>());
        rvTransaction.setAdapter(mTxRecordsAdapter);

        rvBuy.setLayoutManager(new LinearLayoutManager(mActivity));
        mTxBuyAdapter = new TxDataAdapter(R.layout.item_transaction_buy_data, new ArrayList<>());
        rvBuy.setAdapter(mTxBuyAdapter);

        rvSell.setLayoutManager(new LinearLayoutManager(mActivity));
        mTxSellAdapter = new TxDataAdapter(R.layout.item_transaction_sell_data, new ArrayList<>());
        rvSell.setAdapter(mTxSellAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getTxBuyData();
        mPresenter.getTxSellData();
        mPresenter.getCommissionTxRecords(false);
    }

    @OnClick({R.id.tv_symbol, R.id.iv_symbol, R.id.tv_buy, R.id.tv_sell, R.id.bt_confirm, R.id.tv_latest_price, R.id.iv_k_line, R.id.tv_latest_cny, R.id.fl_keep_num_set, R.id.fl_showing_set, R.id.tv_all_transaction, R.id.cb_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_symbol:
            case R.id.iv_symbol:
                selectCoin();
                break;

            case R.id.tv_buy:
                processTxBasicInfo(true);
                break;

            case R.id.tv_sell:
                processTxBasicInfo(false);
                break;

            case R.id.bt_confirm:
                txExchange();
                break;

            case R.id.iv_k_line:
                startActivity(new Intent(mActivity, ChartActivity.class));
                break;

            case R.id.tv_latest_price:
            case R.id.tv_latest_cny:
                etPrice.setText(tvLatestPrice.getText());
                startScaleAnimation();
                break;

            case R.id.fl_keep_num_set:
                mShowBottomType = 0;
                showBottomListDialog();
                break;

            case R.id.fl_showing_set:
                mShowBottomType = 1;
                showBottomListDialog();
                break;

            case R.id.tv_all_transaction:
                startActivity(new Intent(mActivity, HistoryRecordActivity.class));
                break;

            case R.id.cb_like:
                if (cbLike.isChecked()) {
                    ToastUtils.showShort(mActivity.getString(R.string.tips_add_success));
                } else {
                    ToastUtils.showShort(mActivity.getString(R.string.tips_delete_success));
                }
                break;
        }
    }

    /**
     * 选择交易对
     */
    private void selectCoin() {
        if (mCoinSelectDialog == null) {
            mCoinSelectDialog = new CoinSelectDialog(mActivity, new CoinSelectDialog.CoinSelectListener() {
                @Override
                public void select(String coinFrom, String coinTo, String price) {
                    mSymbolFrom = coinFrom;
                    mSymbolTo = coinTo;
                }
            });
        }
        mCoinSelectDialog.show();
    }

    /**
     * 处理交易基础信息
     */
    private void processTxBasicInfo(boolean isBuy) {
        if (isBuy) {
            mIsBuy = true;
            tvBuy.setSelected(true);
            tvSell.setSelected(false);
            btConfirm.setText(getString(R.string.tips_buy, mSymbolFrom));
            btConfirm.setBackgroundResource(R.drawable.shape_transaction_buy_bg);
            sb.setProgressDrawable(getResources().getDrawable(R.drawable.shape_seekbar_progress_buy));
            sb.setThumb(getResources().getDrawable(R.drawable.shape_seekbar_thumb_buy));
        } else {
            mIsBuy = false;
            tvBuy.setSelected(false);
            tvSell.setSelected(true);
            btConfirm.setText(getString(R.string.tips_sell, mSymbolFrom));
            btConfirm.setBackgroundResource(R.drawable.shape_transaction_sell_bg);
            sb.setProgressDrawable(getResources().getDrawable(R.drawable.shape_seekbar_progress_sell));
            sb.setThumb(getResources().getDrawable(R.drawable.shape_seekbar_thumb_sell));
        }
        if (!TextUtils.isEmpty(mSymbolFrom)) {
            //getBalance(mSymbolFrom);
            //getBalance(mSymbolTo);
        }
        etAmount.setText("");
    }

    private void txExchange() {
        String price = ncv.getPrice();
        if (TextUtils.isEmpty(price)) {
            ToastUtils.showShort(R.string.tips_input_price);
            return;
        }

        if (MathUtil.equals(price, 0)) {
            ToastUtils.showShort(R.string.tips_input_no_price);
            return;
        }

        String amount = etAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            ToastUtils.showShort(R.string.tips_input_amount);
            return;
        }

        if (!MathUtil.greaterEqualsThan(amount, 0.0100)) {
            ToastUtils.showShort(R.string.tips_min_tx_size);
            return;
        }


        double txAmount = MathUtil.multiply(amount, price).doubleValue();

        if (!MathUtil.greaterEqualsThan(txAmount, 0.0100)) {
            ToastUtils.showShort(R.string.tips_min_tx_amount);
            return;
        }

        if (mIsBuy && MathUtil.greaterThan(txAmount, mBalanceTo)) {
            ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
            return;
        }

        if (!mIsBuy && MathUtil.greaterThan(amount, mBalanceFrom)) {
            ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
        }
    }

    private void startScaleAnimation() {
        etPrice.setSelection(etPrice.getText().length());
        if (mScaleAnimation == null) {
            mScaleAnimation = new ScaleAnimation(1, 1.2f, 1, 1.3f, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0.5f);
            mScaleAnimation.setDuration(300);
            mScaleAnimation.setFillAfter(false);
            mScaleAnimation.setRepeatCount(0);
        }
        etPrice.startAnimation(mScaleAnimation);
    }

    private void showBottomListDialog() {
        if (mBottomListDialog == null) {
            mBottomListDialog = new BottomListDialog(mActivity);
            mBottomListDialog.setCheckedListener(new BottomListDialog.CheckedListener() {
                @Override
                public void checked(int position, String info) {
                    if (mShowBottomType == 0) {
                        mSelectedNumPosition = position;
                        tvKeepNumSet.setText(getString(R.string.tips_nums, info));
                        //mTxBuyAdapter.setDecimalPoint(mSelectedNumPosition + 1);
                        //mTxSellAdapter.setDecimalPoint(mSelectedNumPosition + 1);
                    } else {
                        mSelectedShowPosition = position;
                        tvShowingSet.setText(info);
                        if (position == 0) {
                            rvBuy.setVisibility(View.VISIBLE);
                            rvSell.setVisibility(View.VISIBLE);
                            mGetAllBuyData = false;
                            mGetAllSellData = false;
                        } else if (position == 1) {
                            rvBuy.setVisibility(View.VISIBLE);
                            rvSell.setVisibility(View.GONE);
                            mGetAllBuyData = true;
                            mGetAllSellData = false;
                        } else {
                            rvBuy.setVisibility(View.GONE);
                            rvSell.setVisibility(View.VISIBLE);
                            mGetAllBuyData = false;
                            mGetAllSellData = true;
                        }
                        //onMsgComing(mLastTxBean);
                    }
                }
            });
        }
        if (mShowBottomType == 0) {
            mBottomListDialog.setData(mSelectedNumPosition, mKeepNums);
        } else {
            mBottomListDialog.setData(mSelectedShowPosition, mShowTypeItems);
        }
        mBottomListDialog.show();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initListener() {
        super.initListener();
        cbLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (TextUtils.isEmpty(mSymbolFrom)) {
                    return;
                }
                /*if (isChecked) {
                    WalletUserDao.addOptionalCoinBean(mSymbolFrom, mSymbolTo);
                } else {
                    WalletUserDao.deleteOptionalCoinBean(mSymbolFrom, mSymbolTo);
                }*/
            }
        });

        mTxBuyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                /*ExchangeDataBean bean = mTxBuyAdapter.getData().get(position);
                if (MathUtil.equalsZero(bean.price)) {
                    return;
                }
                etPrice.setText(DataProcess.keepFourDecimals(bean.price));
                startScaleAnimation();*/
            }
        });

        mTxSellAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               /* ExchangeDataBean bean = mTxSellAdapter.getData().get(position);
                if (MathUtil.equalsZero(bean.price)) {
                    return;
                }
                etPrice.setText(DataProcess.keepFourDecimals(bean.price));
                startScaleAnimation();*/
            }
        });

        mTxRecordsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /*CommissionTxBean.RowsBean rowsBean = (CommissionTxBean.RowsBean) adapter.getData().get(position);
                txExchangeCancel(rowsBean);*/
            }
        });

        etAmount.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                String price = etPrice.getText().toString().trim();
                if (TextUtils.isEmpty(price)) {
                    tvTransactionAmount.setText("- -");
                    return;
                }
                String quantity = s.toString().trim();
                if (TextUtils.isEmpty(quantity)) {
                    tvTransactionAmount.setText("- -");
                    sb.setProgress(0);
                } else {
                    double txAmount = MathUtil.multiply(quantity, price).doubleValue();
                    if (MathUtil.equalsZero(txAmount)) {
                        tvTransactionAmount.setText("- -");
                    } else {
                        tvTransactionAmount.setText(getString(R.string.tips_coin_symbol, DataProcess.keepFourDecimals(txAmount), mSymbolTo));
                    }
                    if (mIsBuy && MathUtil.greaterThan(txAmount, mBalanceTo)) {
                        ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
                    } else if (!mIsBuy && MathUtil.greaterThan(quantity, mBalanceFrom)) {
                        ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
                    }

                    if (mIsBuy) {
                        if (MathUtil.equalsZero(mMaxBuyAmount)) {
                            sb.setProgress(0);
                            return;
                        }
                        double progress = MathUtil.divide(quantity, mMaxBuyAmount).doubleValue() * 100f;
                        sb.setProgress((int) Math.min(progress, 100));
                    } else {
                        if (MathUtil.equalsZero(mMaxSellAmount)) {
                            sb.setProgress(0);
                            return;
                        }
                        double progress = MathUtil.divide(quantity, mMaxSellAmount).doubleValue() * 100f;
                        sb.setProgress((int) Math.min(progress, 100));
                    }
                }
            }
        });

        etPrice.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                String price = s.toString().trim();
                if (TextUtils.isEmpty(price)) {
                    tvTransactionAmount.setText("- -");
                } else {
                    String quantity = etAmount.getText().toString().trim();
                    if (MathUtil.equalsZero(price)) {
                        ToastUtils.showShort(R.string.tips_input_no_price);
                        return;
                    }
                    if (TextUtils.isEmpty(quantity)) {
                        tvTransactionAmount.setText("- -");
                        return;
                    }
                    double txAmount = MathUtil.multiply(quantity, price).doubleValue();
                    if (mIsBuy) {
                        mMaxBuyAmount = MathUtil.divide(mBalanceTo, price);
                        tvMaxBalance.setText(getString(R.string.tips_coin_symbol, mMaxBuyAmount.toPlainString(), mSymbolFrom));
                    }
                    tvTransactionAmount.setText(getString(R.string.tips_coin_symbol, DataProcess.keepFourDecimals(txAmount), mSymbolTo));
                    if (mIsBuy && MathUtil.greaterThan(txAmount, mBalanceTo)) {
                        ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
                    } else if (!mIsBuy && MathUtil.greaterThan(quantity, mBalanceFrom)) {
                        ToastUtils.showShort(R.string.tips_insufficient_quantity_available);
                    }
                }
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                etAmount.setText(MathUtil.multiply(seekBar.getProgress() / 100f, mIsBuy ? mMaxBuyAmount : mMaxSellAmount).toPlainString());
                etAmount.setSelection(etAmount.getText().length());
            }
        });
    }

    @Override
    public void setBuyRv(List<TxBean> txBeans) {
        rvBuy.setLayoutManager(new LinearLayoutManager(mActivity));
        TxDataAdapter txDataAdapter = new TxDataAdapter(R.layout.item_transaction_buy_data, txBeans);
        rvBuy.setAdapter(txDataAdapter);
    }

    @Override
    public void setSellRv(List<TxBean> txBeans) {
        rvSell.setLayoutManager(new LinearLayoutManager(mActivity));
        TxDataAdapter txDataAdapter = new TxDataAdapter(R.layout.item_transaction_sell_data, txBeans);
        rvSell.setAdapter(txDataAdapter);
    }

    @Override
    public void setCommissionTxRecordsRv(List<CommissionTxBean> txBeans) {
        mTxRecordsAdapter.setNewData(txBeans);
    }

    @Override
    protected SmartRefreshLayout setRefreshLayout() {
        return srf;
    }

    @Override
    protected BaseQuickAdapter setRefreshAdapter() {
        return mTxRecordsAdapter;
    }
}
