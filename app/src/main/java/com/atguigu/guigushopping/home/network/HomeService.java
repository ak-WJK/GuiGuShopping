package com.atguigu.guigushopping.home.network;

import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface HomeService {

    @GET("json/{url}")
    Observable<CommodityDetailsBean> getHomeDetails(@Path("url") String url);

}
