package com.atguigu.guigushopping.home.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.home.adapter.HomeDetailsAdapter;
import com.atguigu.guigushopping.home.bean.GoodsBean;
import com.atguigu.guigushopping.home.bean.H5Bean;
import com.atguigu.guigushopping.home.bean.WebViewBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.guigushopping.home.adapter.HomeDetailsAdapter.GOODS_BEAN;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_more)
    ImageButton ibMore;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.activity_web_view)
    LinearLayout activityWebView;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    private WebViewBean viewBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        getData();

        setData();

    }

    private void setData() {
        tvTitle.setText(viewBean.getName());
    }

    @OnClick({R.id.ib_back, R.id.ib_more, R.id.ll_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_more:
//                Toast.makeText(WebViewActivity.this, "更多", Toast.LENGTH_SHORT).show();
                if (llRoot.getVisibility() == View.VISIBLE) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }


                break;
        }
    }

    public void getData() {
        viewBean = (WebViewBean) getIntent().getSerializableExtra(HomeDetailsAdapter.WEBVIEW_BEAN);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        webview.addJavascriptInterface(new MyIntertace(), "cyc");

        webview.loadUrl(viewBean.getUrl());


    }


    class MyIntertace {

        @JavascriptInterface
        public void jumpForAndroid(String data) {
            H5Bean h5Bean = JSON.parseObject(data, H5Bean.class);
            int id = h5Bean.getValue().getProduct_id();
            String product_id = "";
            String name = "";
            String cover_price = "";
            if (id % 3 == 0) {
                product_id = "627";
                cover_price = "32.00";
                name = "剑三T恤批发";
            } else if (id % 3 == 1) {
                product_id = "21";
                cover_price = "8.00";
                name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
            } else {
                product_id = "1341";
                cover_price = "50.00";
                name = "【蓝诺】《天下吾双》 剑网3同人本";
            }
            String image = "/supplier/1478873369497.jpg";
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setName(name);
            goodsBean.setCover_price(cover_price);
            goodsBean.setFigure(image);
            goodsBean.setProduct_id(product_id);

            Intent intent = new Intent(WebViewActivity.this, GoodsInfoActivity.class);
            intent.putExtra(GOODS_BEAN, goodsBean);
            startActivity(intent);

        }


    }
}
