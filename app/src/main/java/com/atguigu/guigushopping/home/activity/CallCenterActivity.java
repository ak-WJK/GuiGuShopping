package com.atguigu.guigushopping.home.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atguigu.guigushopping.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallCenterActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_center);
        ButterKnife.bind(this);

        initWebView();
    }

    private void initWebView() {
        WebSettings webSettings = webview.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(true);

        webview.setWebViewClient(new WebViewClient() {

            //当页面加载完成的时候回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


            /*
            * 对网页中的超链接按钮的相应,当按下某个连接时WebViewClient会调用这个方法,并传递url
            *
            * */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;


            }
        });


        webview.loadUrl("http://www6.53kf.com/webCompany.php?arg=10007377&style=2&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fatguigu.com%2F&keyword=&tfrom=1&tpl=crystal_blue&uid=35fd8e6a8f0f3e7a1caedcc583f2f7a7&timeStamp=1488340364404&ucust_id=");


    }
}
