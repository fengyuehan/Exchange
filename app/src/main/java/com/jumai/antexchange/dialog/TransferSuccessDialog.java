package com.jumai.antexchange.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jumai.antexchange.R;
import com.jumai.antexchange.view.TransferSuccessCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName TransferSuccessDialog
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferSuccessDialog extends Dialog {

    @BindView(R.id.tv_info)
    TextView tvInfo;

    private Context mContext;
    private TransferSuccessCallBack mTransferSuccessCallBack;
    private String mInfo;

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public TransferSuccessDialog(@NonNull Context context, TransferSuccessCallBack mTransferSuccessCallBack) {
        super(context);
        this.mContext = context;
        this.mTransferSuccessCallBack = mTransferSuccessCallBack;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_transfer_success, null);
        super.setContentView(mView);
        ButterKnife.bind(this, mView);
        setCanceledOnTouchOutside(false);
        tvInfo.setText(mInfo);
    }


    @OnClick({R.id.tv_cancel, R.id.tv_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_transfer:
                mTransferSuccessCallBack.callBack();
                dismiss();
                break;
        }
    }
}
