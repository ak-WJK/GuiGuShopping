package com.atguigu.guigushopping.user.fragment;

import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushopping.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/11.
 */

public class UserFragment extends BaseFragment {
    TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("用户页面");

    }
}
