package com.atguigu.guigushopping.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;
import com.atguigu.guigushopping.utils.NetConstants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

class MyPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<CommodityDetailsBean.ResultBean.ActInfoBean> actDatas;
    private OnPagerItemClickListener listener;

    public MyPagerAdapter(Context context, List<CommodityDetailsBean.ResultBean.ActInfoBean> actDatas) {

        this.context = context;
        this.actDatas = actDatas;
    }

    @Override
    public int getCount() {
        return actDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        String icon_url = actDatas.get(position).getIcon_url();

        Glide.with(context)
                .load(NetConstants.BASE_URL_IMAGE + icon_url)
                .into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPagerItemClick(position);
                }

            }
        });




        return imageView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public interface OnPagerItemClickListener {

        void onPagerItemClick(int position);

    }

    public void setOnPagerItemClickListener(OnPagerItemClickListener listener) {

        this.listener = listener;
    }
}