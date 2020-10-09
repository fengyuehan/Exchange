package com.jumai.antexchange.ui.areacode;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.AreaCodeBean;

import java.util.List;

/**
 * @ClassName AreaCodeView
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public interface AreaCodeView extends IBaseView {
    void getAreaCodeSuccess(List<AreaCodeBean> list);
    void onError(String message);
}
