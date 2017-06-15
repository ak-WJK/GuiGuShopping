package com.atguigu.guigushopping.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Time extends AppCompatActivity {

    private CompositeSubscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        initData();
    }

    private void initData() {
        mSubscription = new CompositeSubscription();

        Subscription timeSub = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                        LogUtils.d("时间" + aLong);


                        long currentTime = System.currentTimeMillis();
                        long endtime = 0;
                        if (endtime >= currentTime) {

                            //跟新时间
                        } else {
                            //时间超
                            if (!mSubscription.isUnsubscribed()) {
                                mSubscription.unsubscribe();
                            }
                        }

                    }
                });

        //timeSub.unsubscribe();
        mSubscription.add(timeSub);


    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }
}
