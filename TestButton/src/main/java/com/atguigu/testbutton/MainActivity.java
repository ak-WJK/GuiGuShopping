package com.atguigu.testbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.add_sub_view)
    AddSubView addSubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addSubView.setOnViewChangeListener(new AddSubView.onViewChangeListener() {
            @Override
            public void viewChangeListener(int value) {
                Toast.makeText(MainActivity.this, "value==" + value, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
