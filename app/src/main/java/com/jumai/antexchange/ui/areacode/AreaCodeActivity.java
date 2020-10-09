package com.jumai.antexchange.ui.areacode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.AreaCodeBean;
import com.jumai.antexchange.ui.areacode.adapter.AreaCodeAdapter;
import com.jumai.antexchange.utils.TextWatchListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName AreaCodeActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class AreaCodeActivity extends BaseActivity<AreaCodeView, AreaCodePresenter> implements AreaCodeView {


    @BindView(R.id.et_search_country)
    EditText etSearchCountry;
    @BindView(R.id.rv)
    RecyclerView rv;

    private AreaCodeAdapter mAreaCodeAdapter;
    private List<AreaCodeBean> mData;
    private String mKeyWord = "";
    public static final String AREA_CODE = "area_code";
    public static final String COUNTRY = "country";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_area_code;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mPresenter.getAreaCode(mKeyWord);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAreaCodeAdapter = new AreaCodeAdapter(mData);
        rv.setAdapter(mAreaCodeAdapter);
        mAreaCodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(AREA_CODE, ((AreaCodeBean)adapter.getData().get(position)).getAreaCode());
                intent.putExtra(COUNTRY,((AreaCodeBean)adapter.getData().get(position)).getCountry());
                startActivityForResult(intent,0);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etSearchCountry.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0){
                    mKeyWord = s.toString();
                    mPresenter.getAreaCode(mKeyWord);
                }else {
                    mKeyWord = "";
                    mPresenter.getAreaCode(mKeyWord);
                }
            }
        });
    }

    @Override
    public void getAreaCodeSuccess(List<AreaCodeBean> list) {
        mData = list;
    }

    @Override
    public void onError(String message) {
        Toast.makeText(AreaCodeActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
