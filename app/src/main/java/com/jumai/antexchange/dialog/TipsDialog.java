package com.jumai.antexchange.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jumai.antexchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.AutoSize;

/**
 * @author yf
 * @date 2019/9/29/029
 * 描述：
 */
public class TipsDialog extends Dialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    private Context mContext;
    private DSelectedListener mListener;

    public TipsDialog(@NonNull Context context) {
        this(context, 0);
    }

    private TipsDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.CustomDialog);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        setContentView(R.layout.dialog_tips);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_cancel, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                if (mListener != null) {
                    mListener.onCancel();
                }
                dismiss();
                break;
            case R.id.bt_confirm:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                dismiss();
                break;
        }
    }

    public void setTitleGone(boolean gone) {
        tvTitle.setVisibility(gone ? View.GONE : View.VISIBLE);
    }

    public void setTitle(int title) {
        tvTitle.setText(title);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setMsg(int msg) {
        tvMsg.setText(msg);
    }

    public void setMsg(String msg) {
        tvMsg.setText(msg);
    }

    @Override
    public void show() {
        AutoSize.autoConvertDensity((Activity) mContext, 375, true);
        super.show();
    }

    public void setSelectedListener(DSelectedListener listener) {
        mListener = listener;
    }
}
