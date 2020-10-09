package com.jumai.antexchange.ui.transcation;

import android.content.Context;

import com.github.fujianlian.klinechart.DataHelper;
import com.github.fujianlian.klinechart.KLineEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/26/026
 * 描述：
 */
public class ChartPresenter extends BasePresenter<ChartView> {
    private AppApi mAppApi;

    @Inject
    public ChartPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    private List<KLineEntity> datas = null;

    public String getStringFromAssert(Context context, String fileName) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            return new String(buffer, 0, buffer.length, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<KLineEntity> getALL(Context context) {
        if (datas == null) {
            final List<KLineEntity> data = new Gson().fromJson(getStringFromAssert(context, "ibm.json"), new TypeToken<List<KLineEntity>>() {
            }.getType());
            DataHelper.calculate(data);
            datas = data;
        }
        return datas;
    }

    /**
     * 分页查询
     *
     * @param context
     * @param offset  开始的索引
     * @param size    每次查询的条数
     */
    public List<KLineEntity> getData(Context context, int offset, int size) {
        List<KLineEntity> all = getALL(context);
        List<KLineEntity> data = new ArrayList<>();
        int start = Math.max(0, all.size() - 1 - offset - size);
        int stop = Math.min(all.size(), all.size() - offset);
        for (int i = start; i < stop; i++) {
            data.add(all.get(i));
        }
        return data;
    }
}
