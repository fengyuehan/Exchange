package com.jumai.antexchange.ui.login;

import com.jumai.antexchange.Constants;
import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.model.bean.MoviesBean;
import com.jumai.antexchange.model.bean.ResultBean;
import com.jumai.antexchange.utils.net.CallBack;
import com.jumai.antexchange.utils.net.util.RxUtil;

import javax.inject.Inject;


/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private AppApi mAppApi;

    @Inject
    public LoginPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void login(String phone,String password,String type) {
        getView().showLoading();
        mAppApi.getMovies("深圳", 1, 10)
                .compose(RxUtil.lifecycle(getView()))
                .compose(RxUtil.io2main())
                .subscribe(new CallBack<ResultBean<MoviesBean>>() {
                    @Override
                    public void onSuccess(ResultBean<MoviesBean> moviesBeanResultBean) {
                        getView().closeLoading();
                        if (Constants.CODE_OK.equals(moviesBeanResultBean.code)){
                            getView().login(moviesBeanResultBean.data.toString());
                        }
                    }

                    @Override
                    public void onFail(int code, String message) {
                        getView().closeLoading();
                    }
        });

    }
}
