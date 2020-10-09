package com.jumai.antexchange.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.model.adapter.BottomListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.utils.ScreenUtils;

/**
 * Created by yf on 2018/11/22.
 * 描述：底部弹出列表选择
 */
public class BottomListDialog extends Dialog {

    @BindView(R.id.rv)
    RecyclerView rv;

    private Context mContext;
    private BottomListAdapter mAdapter;
    private CheckedListener mListener;

    public BottomListDialog(@NonNull Context context) {
        this(context, null);
    }

    private BottomListDialog(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private BottomListDialog(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, R.style.CustomDialog);
        mContext = context;
        initView();
    }

    public void setData(int selectedPosition, List<String> data) {
        if (mAdapter != null) {
            mAdapter.updateData(selectedPosition, data);
        }
    }

    public void setCheckedListener(CheckedListener listener) {
        mListener = listener;
    }

    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_list, null);
        setContentView(contentView);
        ButterKnife.bind(this);
        //显示在底部
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = ScreenUtils.getScreenSize(mContext)[0];
        getWindow().setAttributes(p);


        //底部弹出动画
        getWindow().setWindowAnimations(R.style.BottomInOut);

        mAdapter = new BottomListAdapter(new ArrayList<>());
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mListener != null) {
                mListener.checked(position, mAdapter.getData().get(position));
            }
            dismiss();
        });
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        dismiss();
    }

    public interface CheckedListener {
        void checked(int position, String info);
    }

    @Override
    public void show() {
        AutoSize.autoConvertDensity((Activity) mContext, 375, true);
        super.show();
    }
}
