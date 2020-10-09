package com.jumai.antexchange.dagger;

import com.jumai.antexchange.ui.areacode.AreaCodeActivity;
import com.jumai.antexchange.ui.asset.AddressActivity;
import com.jumai.antexchange.ui.asset.AddressAddActivity;
import com.jumai.antexchange.ui.ipSetting.IpSettingActivity;
import com.jumai.antexchange.ui.asset.CoinOutActivity;
import com.jumai.antexchange.ui.asset.coindetail.CoinDetailActivity;
import com.jumai.antexchange.ui.asset.coinlist.CoinListActivity;
import com.jumai.antexchange.ui.asset.detail.SingleDetailAcivity;
import com.jumai.antexchange.ui.asset.legalcoindetail.LegalCoinDetailActivity;
import com.jumai.antexchange.ui.asset.rechargecoin.RechargeCoinActivity;
import com.jumai.antexchange.ui.asset.transfer.TransferCoinActivity;
import com.jumai.antexchange.ui.asset.transferrecord.TransferCoinRecordActivity;
import com.jumai.antexchange.ui.historyrecord.activity.OrderDetailActivity;
import com.jumai.antexchange.ui.kyc.IDcardcertification.IDCardCertificationActivity;
import com.jumai.antexchange.ui.kyc.KYCCertificationActivity;
import com.jumai.antexchange.ui.kyc.countrycertification.CountryCertificationActivity;
import com.jumai.antexchange.ui.kyc.emilecertification.EmileCertificationActivity;
import com.jumai.antexchange.ui.kyc.phonecertification.PhoneCertificationActivity;
import com.jumai.antexchange.ui.mine.about.AboutUsActivity;
import com.jumai.antexchange.ui.mine.account_center.TradeSettingActivity;
import com.jumai.antexchange.ui.mine.set.SetActivity;
import com.jumai.antexchange.ui.resetpassword.ResetPasswordActivity;
import com.jumai.antexchange.ui.forgetpassword.ForgetPasswordActivity;
import com.jumai.antexchange.ui.historyrecord.activity.HistoryRecordActivity;
import com.jumai.antexchange.ui.login.LoginActivity;
import com.jumai.antexchange.ui.main.MainActivity;
import com.jumai.antexchange.ui.market.edit.CoinEditActivity;
import com.jumai.antexchange.ui.market.search.CoinSearchActivity;
import com.jumai.antexchange.ui.mine.account_center.AccountSettingActivity;
import com.jumai.antexchange.ui.mine.account_center.PhoneEmailVerificationActivity;
import com.jumai.antexchange.ui.passwordsetting.PassWordSettingActivity;
import com.jumai.antexchange.ui.register.emileregister.EmileRegisterActivity;
import com.jumai.antexchange.ui.register.phoneregister.PhoneRegisterActivity;
import com.jumai.antexchange.ui.splash.SplashActivity;
import com.jumai.antexchange.ui.transcation.ChartActivity;
import com.jumai.antexchange.ui.verification.SafeVerificationActivity;

import dagger.Component;

/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
@ActivityScoped
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(IpSettingActivity ipSettingActivity);

    void inject(HistoryRecordActivity historyRecordActivity);

    void inject(CoinSearchActivity coinSearchActivity);

    void inject(CoinEditActivity coinEditActivity);

    void inject(ChartActivity activity);

    void inject(CoinOutActivity activity);

    void inject(EmileRegisterActivity emileRegisterActivity);

    void inject(PhoneRegisterActivity phoneRegisterActivity);

    void inject(PassWordSettingActivity passWordSettingActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(SafeVerificationActivity safeVerificationActivity);

    void inject(AreaCodeActivity areaCodeActivity);

    void inject(ResetPasswordActivity resetPasswordActivity);

    void inject(CoinDetailActivity coinDetailActivity);

    void inject(LegalCoinDetailActivity legalCoinDetailActivity);

    void inject(SingleDetailAcivity singleDetailAcivity);

    void inject(RechargeCoinActivity rechargeCoinActivity);

    void inject(CoinListActivity coinListActivity);

    void inject(TransferCoinActivity transferCoinActivity);

    void inject(TransferCoinRecordActivity transferCoinRecordActivity);

    void inject(AboutUsActivity aboutUsActivity);

    void inject(SetActivity setActivity);

    void inject(OrderDetailActivity orderDetailActivity);

    void inject(KYCCertificationActivity kycCertificationActivity);

    void inject(PhoneCertificationActivity phoneCertificationActivity);

    void inject(IDCardCertificationActivity idCardCertificationActivity);

    void inject(EmileCertificationActivity emileCertificationActivity);

    void inject(CountryCertificationActivity countryCertificationActivity);

    void inject(AddressActivity activity);

    void inject(AddressAddActivity activity);

    void inject(AccountSettingActivity activity);

    void inject(PhoneEmailVerificationActivity activity);

    void inject(TradeSettingActivity tradeSettingActivity);
}
