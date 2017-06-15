package com.atguigu.guigushopping.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.atguigu.guigushopping.shoppingcart.utils.CartStorage;
import com.atguigu.guigushopping.shoppingcart.view.AddSubView;
import com.atguigu.guigushopping.utils.NetConstants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private static ArrayList<GoodsBean> datas;
    private final CheckBox checkboxAll;
    private final TextView tvShopcartTotal;
    private final CheckBox all;


    public ShoppingCartAdapter(Context mContext, ArrayList<GoodsBean> datas, CheckBox checkboxAll, TextView tvShopcartTotal, CheckBox all) {

        this.mContext = mContext;
        this.datas = datas;
        this.checkboxAll = checkboxAll;
        this.tvShopcartTotal = tvShopcartTotal;
        this.all = all;

        showTotalPrice();
        //校验是否全选
        allCheck();

    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计" + getTotalPrice());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_cart, null);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHodler myViewHodler = (MyViewHodler) holder;

        //1.根据位置得到数据
        final GoodsBean goodsBean = datas.get(position);

        myViewHodler.cbGov.setChecked(goodsBean.isChecked());


        //2.绑定数据
        //图片
        Glide.with(mContext)
                .load(NetConstants.BASE_URL_IMAGE + goodsBean.getFigure())
                .into(myViewHodler.ivGov);
        myViewHodler.tvDescGov.setText(goodsBean.getName());
        //设置价格
        myViewHodler.tvPriceGov.setText("￥" + goodsBean.getCover_price());


        myViewHodler.AddSubView.setValue(goodsBean.getNumber());
        myViewHodler.AddSubView.setMinValue(1);
        myViewHodler.AddSubView.setMaxValue(20);

        myViewHodler.AddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                goodsBean.setNumber(value);

                showTotalPrice();
            }

        });


        myViewHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoodsBean goodsBean = datas.get(position);

                goodsBean.setChecked(!goodsBean.isChecked());

                notifyItemChanged(position);


                showTotalPrice();

                //校验是否全选
                allCheck();
            }
        });


    }

    public void allCheck() {

        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isChecked()) {
                    all.setChecked(false);
                    checkboxAll.setChecked(false);
                } else {
                    number++;
                }
            }

            if (number == datas.size()) {
                all.setChecked(true);
                checkboxAll.setChecked(true);
            }


        } else {
            all.setChecked(false);
            checkboxAll.setChecked(false);
        }


    }

    public void allCheck_none(boolean isCheck) {


        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setChecked(isCheck);
                notifyItemChanged(i);
            }


        } else {
            checkboxAll.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public double getTotalPrice() {

        double result = 0;

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    result = result + goodsBean.getNumber() * Double.parseDouble(goodsBean.getCover_price());
                }
            }
        }


        return result;
    }

    public void deleteData() {
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {

                GoodsBean goodsBean = datas.get(i);

                if (goodsBean.isChecked()) {

                    datas.remove(goodsBean);

                    CartStorage.getInstance(mContext).deleteData(goodsBean);

                    notifyItemChanged(i);

                    i--;
                }
            }

        }


    }


    static class MyViewHodler extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.AddSubView)
        com.atguigu.guigushopping.shoppingcart.view.AddSubView AddSubView;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
