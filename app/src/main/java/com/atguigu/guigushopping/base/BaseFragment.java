package com.atguigu.guigushopping.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/11.
 */

public abstract class BaseFragment extends Fragment {
    public Context context;

    //订阅
    public Subscription subscription;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();

    }

    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}