package com.jumai.antexchange.ui.home;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.bean.BannerBean;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.model.bean.HomeFunctionBean;
import com.jumai.antexchange.model.bean.HomeRecommendBean;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private AppApi mAppApi;

    @Inject
    public HomePresenter(AppApi appApi) {
        mAppApi = appApi;
    }


    public void getBannerData() {
        ArrayList<BannerBean> bannerBeans = new ArrayList<>();
        bannerBeans.add(new BannerBean("https://tuchong.pstatp.com/447069/f/31915641.jpg", "http://119.23.224.19:8889/jm-active/active_one.html"));
        bannerBeans.add(new BannerBean("https://tuchong.pstatp.com/1517613/f/16817169.jpg", "http://119.23.224.19:8889/jm-active/active_tow.html"));
        bannerBeans.add(new BannerBean("https://tuchong.pstatp.com/1370450/f/334400778.jpg", "http://119.23.224.19:8889/jm-active/active_one.html"));
        getView().getBannerData(bannerBeans);
    }

    public void getTextBannerData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("关于恢复XZC和ETH提币业务的公告");
        strings.add("涨幅榜");
        strings.add("帮助中心");
        getView().initTextBanner(strings);
    }

    public void getVpRecommendData() {
        ArrayList<HomeRecommendBean> strings = new ArrayList<>();
        strings.add(new HomeRecommendBean("BTC/USDT", 1032.45, 5.12));
        strings.add(new HomeRecommendBean("ETH/USDT", 252.89, 0.23));
        strings.add(new HomeRecommendBean("OMG/USDT", 45.23, 5.86));
        strings.add(new HomeRecommendBean("ETH/USDT", 5698.12, 0.56));
        strings.add(new HomeRecommendBean("OMG/USDT", 4153.89, 2.36));
        strings.add(new HomeRecommendBean("BTC/USDT", 1568.78, 5.89));
        strings.add(new HomeRecommendBean("OMG/USDT", 45.69, 4.12));
        strings.add(new HomeRecommendBean("ETH/USDT", 555.23, 7.89));
        getView().setVpRecommend(strings);
    }

    public void getFunctionData() {
        ArrayList<HomeFunctionBean> strings = new ArrayList<>();
        strings.add(new HomeFunctionBean("BTC/USDT", "币币交易", false, R.drawable.icon_coin_transaction));
        strings.add(new HomeFunctionBean("ETH/USDT", "资产划转", false, R.drawable.icon_asset_transfer));
        strings.add(new HomeFunctionBean("OMG/USDT", "推荐返佣", true, R.drawable.icon_referral_commission));
        strings.add(new HomeFunctionBean("ETH/USDT", "帮助中心", false, R.drawable.icon_help_center));
        getView().setFunctions(strings);
    }
}
