package com.atguigu.guigushopping.shoppingcart.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.app.MyApplication;
import com.atguigu.guigushopping.base.BaseFragment;
import com.atguigu.guigushopping.home.activity.MainActivity;
import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.atguigu.guigushopping.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.guigushopping.shoppingcart.utils.CartStorage;
import com.atguigu.guigushopping.shoppingcart.view.LinearLayoutManagerWrapper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.atguigu.guigushopping.R.id.ll_empty_shopcart;

/**
 * Created by Administrator on 2017/6/11.
 */

public class ShoppingCartFragment extends BaseFragment {
    private static final int ACTION_EDIT = 1;
    public static final int ACTION_COMPLETE = 2;
    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    Unbinder unbinder;
    private ArrayList<GoodsBean> datas;
    private ShoppingCartAdapter adapter;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_shopping_cart, null);
        unbinder = ButterKnife.bind(this, view);

        //设置tag
        tvShopcartEdit.setTag(ACTION_EDIT);


        return view;
    }


    @Override
    public void initData() {
//        textView.setText("购物车");

//        List<GoodsBean> list = CartStorage.getInstance(MyApplication.mContext).getAllData();
//                for (int i=0;i<list.size();i++){
//                    GoodsBean goodsBean = list.get(i);
//                    Log.e("TAG",goodsBean.toString());
//                }


        showData();


    }

    private void showData() {
        datas = (ArrayList<GoodsBean>) CartStorage.getInstance(MyApplication.mContext).getAllData();
        if (datas != null && datas.size() > 0) {

            llEmptyShopcart.setVisibility(View.GONE);

            adapter = new ShoppingCartAdapter(MyApplication.mContext, datas, checkboxAll, tvShopcartTotal, checkboxDeleteAll);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
//            recyclerview.setLayoutManager(new LinearLayoutManager(MyApplication.mContext, LinearLayout.VERTICAL, false));
            recyclerview.setLayoutManager(new LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false));

        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.ll_check_all, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.ll_delete, R.id.tv_empty_cart_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:

                int tag = (int) tvShopcartEdit.getTag();
                if (tag == ACTION_EDIT) {

                    showDelete();
                } else {
                    hideDelete();

                }

                break;
            case R.id.checkbox_all:

                boolean checked = checkboxAll.isChecked();

                adapter.allCheck_none(checked);

                adapter.showTotalPrice();

                break;
            case R.id.btn_check_out:
                break;
            case R.id.ll_check_all:
                break;
            case R.id.checkbox_delete_all:
                boolean checkboxDeleteAllChecked = checkboxDeleteAll.isChecked();

                adapter.allCheck_none(checkboxDeleteAllChecked);

                break;
            case R.id.btn_delete:
                adapter.deleteData();
                adapter.allCheck();
                showEmpty();


                break;
            case R.id.btn_collection:

                break;
            case R.id.ll_delete:

                break;
            case R.id.tv_empty_cart_tobuy:
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("checkedid", R.id.rb_home);
                startActivity(intent);

                break;
        }
    }

    private void showEmpty() {
        if (datas == null || datas.size() == 0) {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }


    }


    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);
        //显示结算布局
        llCheckAll.setVisibility(View.VISIBLE);
        //隐藏删除布局
        llDelete.setVisibility(View.GONE);

        adapter.allCheck_none(true);

        adapter.allCheck();

        adapter.showTotalPrice();


    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);

        //隐藏结算布局
        llCheckAll.setVisibility(View.GONE);

        //显示删除布局
        llDelete.setVisibility(View.VISIBLE);


        adapter.allCheck_none(false);

        adapter.allCheck();

        adapter.showTotalPrice();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();


    }
}
