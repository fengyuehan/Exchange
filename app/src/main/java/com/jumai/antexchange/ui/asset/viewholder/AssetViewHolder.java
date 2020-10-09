package com.jumai.antexchange.ui.asset.viewholder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.crazysunj.cardslideview.CardHolder;
import com.crazysunj.cardslideview.CardViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.CoinAccountBean;

/**
 * @ClassName AssetViewHolder
 * @Description TODO
 * @Author user
 * @Date 2019/9/23
 * @Version 1.0
 */
public class AssetViewHolder implements CardHolder<CoinAccountBean> {

    private ImageView iv_eyes;
    private TextView tv_btc_amount;
    private TextView  tv_cyn_amount;
    private TextView  tv_coin_charging;
    private TextView  tv_withdraw_money;
    private TextView  tv_transfer;
    private LinearLayout ll_coin_bg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.fragment_asset_header,container,false);
    }

    @Override
    public void onBindView(@NonNull CardViewHolder holder, CoinAccountBean data, int position) {
        Log.e("zzf",position +"");
        iv_eyes = holder.getView(R.id.iv_eyes);
        tv_btc_amount = holder.getView(R.id.tv_btc_amount);
        tv_cyn_amount = holder.getView(R.id.tv_cyn_amount);
        tv_coin_charging = holder.getView(R.id.tv_coin_charging);
        tv_withdraw_money = holder.getView(R.id.tv_withdraw_money);
        tv_transfer = holder.getView(R.id.tv_transfer);
        ll_coin_bg = holder.getView(R.id.ll_coin_bg);
        tv_btc_amount.setText(data.getBanalce());
        tv_cyn_amount.setText(data.getAmount());
        if (position % 2 == 0){
            ll_coin_bg.setBackgroundResource(R.drawable.shape_coin_count_bg);
        }else {
            ll_coin_bg.setBackgroundResource(R.drawable.shape_legal_coin_account_bg);
        }
        iv_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_coin_charging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_withdraw_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
