package com.atguigu.guigushopping.home.network;

import android.util.Log;

import com.atguigu.guigushopping.utils.NetConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RequestNet {

    //构造方法私有化
    private RequestNet() {
    }


    //私有化静态内部类
    private static class Instance {

        private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("TAG", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);


        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL+"/")
//                .baseUrl("http://47.93.118.241:8081/android/resources/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    //静态的公共的方法得到对象
    public static Retrofit getInstance() {
        return Instance.RETROFIT;
    }

}
