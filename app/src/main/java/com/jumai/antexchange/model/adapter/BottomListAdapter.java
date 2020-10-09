package com.jumai.antexchange.model.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class BottomListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int mSelectedPosition;

    public BottomListAdapter(@Nullable List<String> data) {
        super(R.layout.item_list, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, String bean) {
        int layoutPosition = helper.getLayoutPosition();
        TextView tv = helper.getView(R.id.tv);
        tv.setSelected(mSelectedPosition == layoutPosition);
        tv.setText(bean);
    }

    public void updateData(int selectedPosition, List<String> data) {
        mSelectedPosition = selectedPosition;
        setNewData(data);
    }
}
