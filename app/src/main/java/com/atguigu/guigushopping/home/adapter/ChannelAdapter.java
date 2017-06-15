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
 * Created by Administrator on 2017/6/12.
 */

public class ChannelAdapter extends BaseAdapter {
    private final Context context;
    private final List<CommodityDetailsBean.ResultBean.ChannelInfoBean> channelDatas;

    public ChannelAdapter(Context context, List<CommodityDetailsBean.ResultBean.ChannelInfoBean> channelDatas) {

        this.context = context;
        this.channelDatas = channelDatas;
    }

    @Override
    public int getCount() {
        return channelDatas.size();
    }

    @Override
    public CommodityDetailsBean.ResultBean.ChannelInfoBean getItem(int position) {
        return channelDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.home_channel_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        CommodityDetailsBean.ResultBean.ChannelInfoBean channelInfoBean = channelDatas.get(position);

        viewHolder.tvChannel.setText(channelInfoBean.getChannel_name());
        Glide.with(context)
                .load(NetConstants.BASE_URL_IMAGE + channelInfoBean.getImage())
                .into(viewHolder.ivChannel);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
