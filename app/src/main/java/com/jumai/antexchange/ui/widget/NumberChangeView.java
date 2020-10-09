package com.jumai.antexchange.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jumai.antexchange.R;
import com.jumai.antexchange.utils.DataProcess;
import com.jumai.antexchange.view.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/10/10/010
 * 描述：
 */
public class NumberChangeView extends LinearLayout {

    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.iv_reduce)
    ImageView ivReduce;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    private static final int ON_LONG_ADD_PRESS = 0;
    private static final int ON_LONG_REDUCE_PRESS = 1;

    public NumberChangeView(Context context) {
        this(context, null);
    }

    public NumberChangeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.l_number_change, this);
        ButterKnife.bind(this);
        setBackgroundResource(R.drawable.shape_transaction_number);
        setGravity(Gravity.CENTER_VERTICAL);
        initListener();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size, CommonUtil.dp2px(getContext(), 40));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        ivAdd.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mHandler.sendEmptyMessage(ON_LONG_ADD_PRESS);
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    mHandler.removeMessages(ON_LONG_ADD_PRESS);
                }
                return true;
            }
        });

        ivReduce.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mHandler.sendEmptyMessage(ON_LONG_REDUCE_PRESS);
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    mHandler.removeMessages(ON_LONG_REDUCE_PRESS);
                }
                return true;
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ON_LONG_ADD_PRESS:
                    changeProcess(true);
                    mHandler.sendEmptyMessageDelayed(ON_LONG_ADD_PRESS, 120);
                    break;
                case ON_LONG_REDUCE_PRESS:
                    changeProcess(false);
                    mHandler.sendEmptyMessageDelayed(ON_LONG_REDUCE_PRESS, 120);
                    break;
            }
        }
    };

    @OnClick({R.id.iv_reduce, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_reduce:
                changeProcess(false);
                break;
            case R.id.iv_add:
                changeProcess(true);
                break;
        }
    }

    private void changeProcess(boolean add) {
        etPrice.requestFocus();
        String price = etPrice.getText().toString().trim();
        if (!TextUtils.isEmpty(price)) {
            double dPrice = Double.valueOf(price);
            if (add) {
                dPrice += 0.01;
            } else {
                dPrice -= 0.01;
                if (dPrice < 0) {
                    dPrice = 0.0;
                }
            }
            etPrice.setText(DataProcess.keepFourDecimals(dPrice));
            etPrice.setSelection(etPrice.getText().length());
        } else {
            if (add) {
                etPrice.setText("0.01");
            }
        }
    }

    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public String getPrice() {
        return etPrice.getText().toString().trim();
    }
}
