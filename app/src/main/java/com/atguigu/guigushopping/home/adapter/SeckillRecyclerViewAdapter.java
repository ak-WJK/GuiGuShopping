package com.atguigu.guigushopping.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.bean.CommodityDetailsBean;
import com.atguigu.guigushopping.utils.NetConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter {
    private static Context context;
    private final CommodityDetailsBean.ResultBean.SeckillInfoBean seckillData;
    private onPagerItemChangerListener listener;

    public SeckillRecyclerViewAdapter(Context context, CommodityDetailsBean.ResultBean.SeckillInfoBean seckillData) {

        this.context = context;
        this.seckillData = seckillData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_seckill_item, null);

        return new SeckillDetailViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        SeckillDetailViewHodler seckillDetailViewHodler = (SeckillDetailViewHodler) viewHolder;

        CommodityDetailsBean.ResultBean.SeckillInfoBean.ListBean listBean = seckillData.getList().get(position);
        seckillDetailViewHodler.setData(listBean);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPagerChangerListener(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return seckillData.getList().size();
    }

    static class SeckillDetailViewHodler extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_figure)
        ImageView ivFigure;
        @BindView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;
        private CommodityDetailsBean.ResultBean.SeckillInfoBean.ListBean data;


        public SeckillDetailViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private long time;

        public void setData(CommodityDetailsBean.ResultBean.SeckillInfoBean.ListBean data) {
            this.data = data;


            Glide.with(context)
                    .load(NetConstants.BASE_URL_IMAGE + data.getFigure())
                    .into(ivFigure);

            tvCoverPrice.setText("￥" + data.getCover_price());
            tvOriginPrice.setText("￥" + data.getOrigin_price());


        }
    }

    interface onPagerItemChangerListener {
        void onPagerChangerListener(int position);
    }

    public void setOnPagerItemChangerListener(onPagerItemChangerListener listener) {



        this.listener = listener;
    }

}
