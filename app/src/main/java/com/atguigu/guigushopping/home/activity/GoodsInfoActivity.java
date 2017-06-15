package com.atguigu.guigushopping.home.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.atguigu.guigushopping.shoppingcart.utils.CartStorage;
import com.atguigu.guigushopping.shoppingcart.view.AddSubView;
import com.atguigu.guigushopping.utils.NetConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.guigushopping.home.adapter.HomeDetailsAdapter.GOODS_BEAN;

public class GoodsInfoActivity extends AppCompatActivity {

    @BindView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @BindView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @BindView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @BindView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @BindView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @BindView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @BindView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @BindView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @BindView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @BindView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @BindView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @BindView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @BindView(R.id.tv_more_share)
    TextView tvMoreShare;
    @BindView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @BindView(R.id.tv_more_home)
    TextView tvMoreHome;
    @BindView(R.id.btn_more)
    Button btnMore;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    private GoodsBean goodsBean;
    private GoodsBean tmpeGoodsBean;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        getAndSetData();


    }

    private void getAndSetData() {
        //得到数据并设置数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);

        String coverPrice = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        String productId = goodsBean.getProduct_id();
        String name = goodsBean.getName();

        //设置图片
        Glide.with(this).load(NetConstants.BASE_URL_IMAGE + figure).into(ivGoodInfoImage);
        tvGoodInfoName.setText(name);
        tvGoodInfoPrice.setText("￥" + coverPrice);


        //设置webView的数据
        setWebViewData(productId);


    }

    private void setWebViewData(String productId) {
        WebSettings webSettings = wbGoodInfoMore.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);

        //设置检索缓存的
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);

        //设置不跳转到系统的浏览器
        wbGoodInfoMore.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        wbGoodInfoMore.loadUrl("http://mp.weixin.qq.com/s/Cf3DrW2lnlb-w4wYaxOEZg");

    }


    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                if (llRoot.getVisibility() == View.VISIBLE) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tv_good_info_callcenter:

                intent = new Intent(this, CallCenterActivity.class);
                startActivity(intent);


                break;
            case R.id.tv_good_info_collection:
                break;
            case R.id.tv_good_info_cart:
                 intent = new Intent(GoodsInfoActivity.this, MainActivity.class);
                intent.putExtra("checkedid", R.id.rb_gouwuche);//跳转到主页面
                startActivity(intent);
                break;
            case R.id.btn_good_info_addcart:
//
//                CartStorage instance = CartStorage.getInstance(MyApplication.mContext);
//                instance.addData(goodsBean);
//

                showPopwindow();

//                Toast.makeText(GoodsInfoActivity.this, "宝贝已添加到购物车...", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_more_share:
                break;
            case R.id.tv_more_search:
                break;
            case R.id.tv_more_home:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("checkedid", R.id.rb_home);//跳转到主页面
                startActivity(intent);
                finish();
                break;
            case R.id.btn_more:
                llRoot.setVisibility(View.GONE);
                break;
        }
    }

    private boolean isExist = false;

    private void showPopwindow() {

        //查找是否存在
        tmpeGoodsBean = CartStorage.getInstance(this).findData(goodsBean.getProduct_id());
        if (tmpeGoodsBean == null) {
            //之前在购物车里面没有
            isExist = false;
            tmpeGoodsBean = goodsBean;
        } else {
            isExist = true;
        }


        //得到布局文件
        View view = LayoutInflater.from(GoodsInfoActivity.this).inflate(R.layout.popupwindow_add_product, null);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        popupWindow.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        // 5 在底部显示
//        popupWindow.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
//                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));


        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        AddSubView nas_goodinfo_num = (AddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);


        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(NetConstants.BASE_URL_IMAGE + goodsBean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(tmpeGoodsBean.getName());
        // 显示价格
        tv_goodinfo_price.setText(tmpeGoodsBean.getCover_price());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(100);//库存100
        nas_goodinfo_num.setValue(tmpeGoodsBean.getNumber());


        nas_goodinfo_num.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                tmpeGoodsBean.setNumber(value);
            }
        });


        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        if (!isExist && tmpeGoodsBean.getNumber() == 1) {
            Toast.makeText(GoodsInfoActivity.this, "请选择您要购买的数量", Toast.LENGTH_SHORT).show();
        }
        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                //添加购物车
                CartStorage.getInstance(GoodsInfoActivity.this).addData(tmpeGoodsBean);
//                Log.e("TAG", "66:" + tmpeGoodsBean.toString());

            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });


        // 5 在底部显示
        popupWindow.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.btn_good_info_addcart),
                Gravity.BOTTOM, 0, 0);


    }


}
