package com.atguigu.guigushopping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;
import com.atguigu.guigushopping.utils.NetConstants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/13.
 */

public class HotGridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<CommodityDetailsBean.ResultBean.HotInfoBean> hotData;

    public HotGridViewAdapter(Context context, List<CommodityDetailsBean.ResultBean.HotInfoBean> hotData) {

        this.context = context;
        this.hotData = hotData;
    }

    @Override
    public int getCount() {
        return hotData.size();
    }

    @Override
    public CommodityDetailsBean.ResultBean.HotInfoBean getItem(int position) {
        return hotData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.home_hot_item, null);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }

        CommodityDetailsBean.ResultBean.HotInfoBean hotInfoBean = hotData.get(position);


        Glide.with(context)
                .load(NetConstants.BASE_URL_IMAGE + hotInfoBean.getFigure())
                .into(viewHodler.ivHot);
        viewHodler.tvName.setText(hotInfoBean.getName());
        viewHodler.tvPrice.setText("￥" + hotInfoBean.getCover_price());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
