package com.atguigu.testbutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.testbutton.R.id.iv_add;
import static com.atguigu.testbutton.R.id.iv_sub;
import static com.atguigu.testbutton.R.id.tv_value;

/**
 * Created by Administrator on 2017/6/14.
 */

public class AddSubView extends LinearLayout {

    private final Context context;
    @BindView(iv_sub)
    ImageView ivSub;
    @BindView(tv_value)
    TextView tvValue;
    @BindView(iv_add)
    ImageView ivAdd;


    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    private onViewChangeListener listener;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

        //注意要加上“”
        tvValue.setText(value + "");


    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        //this--->将布局文件添加到父布局即LinearLayout
        View view = View.inflate(context, R.layout.add_sub_view, this);
        ButterKnife.bind(this, view);


        if (attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (value > 0) {
                setMinValue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (value > 0) {
                setMaxValue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if (addDrawable != null) {
                ivAdd.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if (subDrawable != null) {
                ivSub.setImageDrawable(subDrawable);
            }

        }

    }

    @OnClick({iv_sub, iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case iv_sub:
                subNumber();
                break;
            case iv_add:
                addNumber();
                break;
        }

        if (listener != null) {
            listener.viewChangeListener(value);
        }


    }

    private void subNumber() {
        if (value > minValue) {
            value--;
        }

        tvValue.setText(value + "");
    }

    private void addNumber() {
        if (value < maxValue) {
            value++;
        }
        tvValue.setText(value + "");
    }


    interface onViewChangeListener {
        void viewChangeListener(int value);
    }


    public void setOnViewChangeListener(onViewChangeListener listener) {

        this.listener = listener;
    }

}
