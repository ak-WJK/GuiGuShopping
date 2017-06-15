package com.atguigu.guigushopping.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.guigushopping.R;
import com.atguigu.guigushopping.base.BaseFragment;
import com.atguigu.guigushopping.communtiy.fragment.CommuntiyFragment;
import com.atguigu.guigushopping.home.fragment.HomeFragment;
import com.atguigu.guigushopping.shoppingcart.fragment.ShoppingCartFragment;
import com.atguigu.guigushopping.type.fragment.TypeFragment;
import com.atguigu.guigushopping.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private ArrayList<BaseFragment> fragments;

    /**
     * 记录fragment的位置
     */
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化各个Fragment
        initFragment();
        //设置RadioGroup点击的监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //设置默认选中
        rgMain.check(R.id.rb_home);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_type:
                    position = 1;
                    break;
                case R.id.rb_faxian:
                    position = 2;
                    break;
                case R.id.rb_gouwuche:
                    position = 3;
                    break;
                case R.id.rb_gerencenter:
                    position = 4;
                    break;
            }

            if (fragments != null && fragments.size() > 0) {
                BaseFragment currFragment = fragments.get(position);
                switchFragment(currFragment);
            }

        }
    }

    private Fragment tempFragment;

    private void switchFragment(BaseFragment currFragment) {


        if (currFragment != null) {
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            if (currFragment != tempFragment) {
                if (!currFragment.isAdded()) {

                    if (tempFragment != null) {

                        fm.hide(tempFragment);
                    }

                    fm.add(R.id.framelayout, currFragment);
                } else {
                    if (tempFragment != null) {
                        fm.hide(tempFragment);
                    }
                    fm.show(currFragment);
                }

                fm.commit();
            }

            tempFragment = currFragment;
        }

    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommuntiyFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }


    /**
     * launchMode为singleTask的时候，
     * 通过Intent启到一个Activity,如果系统已经存在一个实例，
     * 系统就会将请求发送到这个实例上，但这个时候，
     * 系统就不会再调用通常情况下我们处理请求数据的onCreate方法，
     * 而是调用onNewIntent方法
     */

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int checkedid = intent.getIntExtra("checkedid", R.id.rb_home);
        switch (checkedid) {
            case R.id.rb_home:
                rgMain.check(R.id.rb_home);
                break;
            case R.id.rb_gouwuche:
                rgMain.check(R.id.rb_gouwuche);
                break;
        }


    }
}
