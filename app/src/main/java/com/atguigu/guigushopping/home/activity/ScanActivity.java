package com.atguigu.guigushopping.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.guigushopping.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.zx_view)
    ZXingView zxView;
    private QRCodeView.Delegate delegate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);


        delegate = new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String s) {

            }

            @Override
            public void onScanQRCodeOpenCameraError() {

            }
        };


        // 设置结果处理
//        QRCodeView mQR = new QRCodeView(this) {
//            @Override
//            public String processData(byte[] bytes, int i, int i1) {
//                return null;
//            }
//        };
//        mQR.setDelegate(delegate);//（如果使用的新版本的依赖，使用这个）
//        //开始读取二维码
//        mQR.startSpot();
//

    }
}
