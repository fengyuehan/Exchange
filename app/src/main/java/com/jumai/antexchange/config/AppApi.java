package com.jumai.antexchange.config;

import com.jumai.antexchange.model.bean.MoviesBean;
import com.jumai.antexchange.model.bean.ResultBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：接口
 */
public interface AppApi {

    @GET("v2/movie/in_theaters")
    Observable<ResultBean<MoviesBean>> getMovies(@Query("city") String city, @Query("start") int start, @Query("count") int count);
}
