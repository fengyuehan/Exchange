package com.jumai.antexchange.ui.home;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.BannerBean;
import com.jumai.antexchange.model.bean.HomeFunctionBean;
import com.jumai.antexchange.model.bean.HomeRecommendBean;

import java.util.ArrayList;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public interface HomeView extends IBaseView {
    void initBanner();

    void getBannerData(ArrayList<BannerBean> bannerBeans);

    void initTextBanner(ArrayList<String> strings);

    void setVpRecommend(ArrayList<HomeRecommendBean> homeRecommendBeans);

    void setFunctions(ArrayList<HomeFunctionBean> homeFunctionBeans);

}
