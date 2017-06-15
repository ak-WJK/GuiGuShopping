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

public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<CommodityDetailsBean.ResultBean.RecommendInfoBean> recommendDatas;

    public RecommendGridViewAdapter(Context context, List<CommodityDetailsBean.ResultBean.RecommendInfoBean> recommendDatas) {

        this.context = context;
        this.recommendDatas = recommendDatas;
    }

    @Override
    public int getCount() {
        return recommendDatas.size();
    }

    @Override
    public CommodityDetailsBean.ResultBean.RecommendInfoBean getItem(int position) {
        return recommendDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.home_recommend_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommodityDetailsBean.ResultBean.RecommendInfoBean recommendInfoBean = recommendDatas.get(position);
        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        Glide.with(context)
                .load(NetConstants.BASE_URL_IMAGE + recommendInfoBean.getFigure())
                .into(viewHolder.ivRecommend);


        return convertView;

    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
