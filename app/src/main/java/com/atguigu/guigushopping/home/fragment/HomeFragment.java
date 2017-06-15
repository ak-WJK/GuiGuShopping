package com.atguigu.guigushopping.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.base.BaseFragment;
import com.atguigu.guigushopping.home.activity.ScanActivity;
import com.atguigu.guigushopping.home.adapter.HomeDetailsAdapter;
import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;
import com.atguigu.guigushopping.home.network.HomeService;
import com.atguigu.guigushopping.home.network.RequestNet;
import com.atguigu.guigushopping.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/11.
 */

public class HomeFragment extends BaseFragment {
    TextView textView;
    @BindView(R.id.tv_home_search)
    TextView tvHomtSearch;
    @BindView(R.id.ll_home_saoyisao)
    LinearLayout llHomeSaoyisao;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Unbinder unbinder;

    @BindView(R.id.ll_home_xiaoxi)
    LinearLayout llHomeXiaoxi;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    private Observer<CommodityDetailsBean> observer;
    private HomeService service;

    private HomeDetailsAdapter homeDetailsAdapter;


    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_fragment_layout, null);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void initData() {
        requestDatas();

    }

    private void requestDatas() {
        service = RequestNet.getInstance().create(HomeService.class);

        if (service != null) {

            unsubscribe();

            observer = new Observer<CommodityDetailsBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.e("TAG", "数据请求失败" + e.getMessage());

                }

                @Override
                public void onNext(CommodityDetailsBean commodityDetailsBeans) {

                    LogUtils.e("TAG", "数据请求成功===" + commodityDetailsBeans.getResult().getSeckill_info().getList().get(0).getName());
//                    anaylsisJson(commodityDetailsBeans);
//                    commodityDetailsBeans.getResult().getSeckill_info().getList().get(0).getName();

                    //向适配器设置数据
                    if (commodityDetailsBeans != null) {
//                        homeDetailsAdapter.setAdapterDatas(commodityDetailsBeans.getResult());


                        //设置recycleView的布局管理器
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);


                        //此句代码是设置对应position位置的item的跨列数

                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position <= 3) {
                                    ivTop.setVisibility(View.GONE);
                                } else {

                                    ivTop.setVisibility(View.VISIBLE);
                                }

                                return 1;
                            }
                        });

                        recyclerview.setLayoutManager(gridLayoutManager);
//                        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


                        homeDetailsAdapter = new HomeDetailsAdapter(context, commodityDetailsBeans.getResult());

                        //设置适配器
                        recyclerview.setAdapter(homeDetailsAdapter);

                    }


//
//


                }
            };
            subscription = service
                    .getHomeDetails("HOME_URL.json")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }


//    private void anaylsisJson(CommodityDetailsBean json) {
//
//
//
//    }


    @OnClick({R.id.tv_home_search, R.id.ll_home_saoyisao, R.id.ll_home_xiaoxi, R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_home_saoyisao:
                Intent intent = new Intent(context, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_xiaoxi:
                Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_top:
                recyclerview.scrollToPosition(0);
                break;

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }


}
