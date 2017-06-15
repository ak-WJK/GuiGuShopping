package com.atguigu.guigushopping.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 * 存储购物信息
 * 用sparseArray
 */

public class CartStorage {

    private static final String JSON_CART = "json_cart";
    private SparseArray<GoodsBean> sparseArray;
    private static CartStorage instance;
    private static Context mContext;


    private CartStorage() {
        sparseArray = new SparseArray<>();

        //从本地加载数据 lsit-->sparesArray
        listToSparesArray();

    }

    private void listToSparesArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }

    }

    public List<GoodsBean> getAllData() {
        List<GoodsBean> datas = new ArrayList<>();
        String saveJson = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(saveJson)) {
            datas = new Gson().fromJson(saveJson, new TypeToken<List<GoodsBean>>() {
            }.getType());

        }


        return datas;
    }

    public static CartStorage getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage();
                }
            }
        }
        return instance;

    }

    /*
    * 添加数据
    *
    * */

    public void addData(GoodsBean goodsBean) {

        GoodsBean tempBean = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempBean != null) {
            tempBean.setNumber(goodsBean.getNumber());
        } else {
            tempBean = goodsBean;
        }
        //内存中更新
        sparseArray.put(Integer.parseInt(tempBean.getProduct_id()), tempBean);

        //同步到本地
        commit();

    }

    /*
    * 删除数据
    *
    *
    * */

    public void deleteData(GoodsBean goodsBean) {
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        commit();
    }

    /*
    *
    * 更新数据
    * */

    public void updateData(GoodsBean goodsBean) {

        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        commit();


    }

    private void commit() {

        List<GoodsBean> goodsBeanList = sparseArrayToList();
        String json = new Gson().toJson(goodsBeanList);
        CacheUtils.putString(mContext, JSON_CART, json);

    }

    private List<GoodsBean> sparseArrayToList() {
        List<GoodsBean> list = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            list.add(goodsBean);
        }


        return list;
    }


    //    private static final String JSON_CART = "json_cart";
//    private Context context;
//    private SparseArray<GoodsBean> sparseArray;
//    private static CartStorage instance;
//
//    public CartStorage(Context context) {
//        this.context = context;
//
//        sparseArray = new SparseArray<>();
//
//        //从本地加载-->list-->SparseArray
//        listToSparseArray();
//
//    }
//
//    private void listToSparseArray() {
//        List<GoodsBean> list = getAllData();
//        for (int i = 0; i < list.size(); i++) {
//            GoodsBean goodsBean = list.get(i);
//            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
//        }
//    }
//
//    public List<GoodsBean> getAllData() {
//        List<GoodsBean> list = new ArrayList<>();
//        String saveJson = CacheUtils.getString(context, JSON_CART);
//        if (!TextUtils.isEmpty(saveJson)) {
//            list = new Gson().fromJson(saveJson, new TypeToken<List<GoodsBean>>() {
//            }.getType());
//        }
//        return list;
//    }
//
//    public static CartStorage getInstance(Context context) {
//        if (instance == null) {
//            synchronized (CartStorage.class) {
//
//                if (instance == null) {
//                    instance = new CartStorage(context);
//                }
//
//            }
//        }
//
//        return instance;
//    }
//
//    /*
//    * 添加数据
//    *
//    *
//    * */
//    public void addData(GoodsBean goodsBean) {
//        GoodsBean temp = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
//        if (temp != null) {
//            temp.setNumber(temp.getNumber() + goodsBean.getNumber());
//
//
//        } else {
//            temp = goodsBean;
//            temp.setNumber(1);
//        }
//
//        //添加到内存
//        sparseArray.put(Integer.parseInt(temp.getProduct_id()), temp);
//
//        //保存到本地
//        commit();
//    }
//
//
//    /*
//    *删除数据
//    *
//    *
//    * */
//    public void deleteDate(GoodsBean goodsBean) {
//        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
//        commit();
//    }
//
//    /*
//    * 修改数据
//    * */
//    public void updataData(GoodsBean goodsBean) {
//        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
//
//        commit();
//    }
//
//
//    private void commit() {
//        List<GoodsBean> list = sparseArrayToList();
//        String json = new Gson().toJson(list);
//        CacheUtils.putString(context, JSON_CART, json);
//    }
//
//    private List<GoodsBean> sparseArrayToList() {
//        List<GoodsBean> list = new ArrayList<>();
//        for (int i = 0; i < sparseArray.size(); i++) {
//
//            GoodsBean goodsBean = sparseArray.valueAt(i);
//            list.add(goodsBean);
//
//        }
//
//        return list;
//
//    }

    /*
    * 查询数据
    * */
    public GoodsBean findData(String product_id) {
        GoodsBean goodsBean = sparseArray.get(Integer.parseInt(product_id));
        return goodsBean;
    }


}

