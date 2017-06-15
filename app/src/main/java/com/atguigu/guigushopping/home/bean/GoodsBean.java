package com.atguigu.guigushopping.home.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/13.
 */

public class GoodsBean implements Serializable {
    private String cover_price;
    private String figure;
    private String name;

    /*
    * 唯一标识
    * */
    private String product_id;

    /*
    * 记录在购物车的购买数量
    * */
    private int number = 1;
    /**
     * 默认是选择
     */
    private boolean isChecked = true;


    public boolean isChecked() {

        return isChecked;
    }


    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public boolean getChecked() {
        return isChecked;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public GoodsBean(String name, String cover_price, String figure, String product_id) {

        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
    }

    public GoodsBean() {
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                '}';
    }
}
