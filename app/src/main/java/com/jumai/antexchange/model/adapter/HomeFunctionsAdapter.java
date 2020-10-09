package com.jumai.antexchange.model.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.HomeFunctionBean;
import com.jumai.antexchange.utils.ImageUtil;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class HomeFunctionsAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> {
    public HomeFunctionsAdapter(List<HomeFunctionBean> data) {
        super(R.layout.item_home_function, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeFunctionBean bean) {
//        ImageUtil.loadHolder(mContext, bean.img, helper.getView(R.id.iv_icon),R.drawable.icon_announcement);
        helper.setImageResource(R.id.iv_icon,bean.resId);
        helper.setText(R.id.tv_name, bean.title)
                .setGone(R.id.tv_tag_hot, bean.hot);
    }
}
