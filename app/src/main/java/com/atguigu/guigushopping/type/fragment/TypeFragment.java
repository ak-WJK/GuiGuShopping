package com.atguigu.guigushopping.type.fragment;

import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/11.
 */

public class TypeFragment extends BaseFragment {
    TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_type_layout, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
