package com.jumai.antexchange.ui.home;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseFragment;
import com.jumai.antexchange.bean.BannerBean;
import com.jumai.antexchange.model.adapter.HomeFunctionsAdapter;
import com.jumai.antexchange.model.adapter.VpRecommendAdapter;
import com.jumai.antexchange.model.adapter.VpTopAdapter;
import com.jumai.antexchange.model.bean.HomeFunctionBean;
import com.jumai.antexchange.model.bean.HomeRecommendBean;
import com.jumai.antexchange.utils.ImageUtil;
import com.jumai.antexchange.view.NestedViewPager;
import com.jumai.antexchange.view.PointIndicatorView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.transformers.Transformer;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jumai.antexchange.model.adapter.VpRecommendAdapter.SIZE;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.tv_banner)
    TextBannerView tvBanner;
    @BindView(R.id.vp_recommend)
    ViewPager vpRecommend;
    @BindView(R.id.rv_function)
    RecyclerView rvFunction;
    @BindView(R.id.tbl)
    TabLayout tbl;
    @BindView(R.id.vp_top)
    NestedViewPager vpTop;
    @BindView(R.id.inv)
    PointIndicatorView inv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {
        initBanner();
        initVpTop();
    }

    @Override
    protected void initData() {
        mPresenter.getBannerData();
        mPresenter.getTextBannerData();
        mPresenter.getVpRecommendData();
        mPresenter.getFunctionData();
    }

    @Override
    public void initBanner() {
        banner.loadImage((banner, model, view, position) -> {
            BannerBean bannerBean = (BannerBean) model;
            ImageUtil.loadFitCenter(mActivity, bannerBean.img, (ImageView) view);
        });
    }

    private void initVpTop() {
        VpTopAdapter vpTopAdapter = new VpTopAdapter(mActivity);
        vpTop.setAdapter(vpTopAdapter);
        tbl.setupWithViewPager(vpTop);
    }


    @Override
    public void getBannerData(ArrayList<BannerBean> bannerBeans) {
        banner.setAutoPlayAble(bannerBeans.size() > 1);
        banner.setIsClipChildrenMode(true);
        banner.setBannerData(R.layout.l_banner, bannerBeans);
    }

    @Override
    public void initTextBanner(ArrayList<String> strings) {
        tvBanner.setDatas(strings);
    }

    @Override
    public void setVpRecommend(ArrayList<HomeRecommendBean> homeRecommendBeans) {
        VpRecommendAdapter vpRecommendAdapter = new VpRecommendAdapter(mActivity, homeRecommendBeans);
        vpRecommend.setAdapter(vpRecommendAdapter);
        inv.setIndicator((int) Math.ceil(homeRecommendBeans.size() / (SIZE * 1f)), vpRecommend);
    }

    @Override
    public void setFunctions(ArrayList<HomeFunctionBean> homeFunctionBeans) {
        HomeFunctionsAdapter homeFunctionsAdapter = new HomeFunctionsAdapter(homeFunctionBeans);
        rvFunction.setLayoutManager(new GridLayoutManager(mActivity, 4));
        rvFunction.setAdapter(homeFunctionsAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
        tvBanner.startViewAnimator();
    }

    @Override
    protected void initListener() {
        super.initListener();
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                ToastUtils.showShort("选中：" + position);
            }
        });
        tvBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {

            }
        });

        vpTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
        tvBanner.stopViewAnimator();
    }
}
