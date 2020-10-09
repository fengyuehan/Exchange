package com.jumai.antexchange.base;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public interface IBaseRefreshView extends IBaseView {

    void refreshOrLoadMoreSuccess(List data);

    void refreshOrLoadMoreFail();

    void refreshOrLoadMoreError();
}
