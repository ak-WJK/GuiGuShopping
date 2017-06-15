package com.atguigu.guigushopping.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.atguigu.guigushopping.home.activity.MainActivity;
import com.atguigu.guigushopping.R;

public class WelcomeActivity extends AppCompatActivity {
    private RelativeLayout welcome;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = (RelativeLayout) findViewById(R.id.welcome);
        setAnimation();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        }, 1500);

    }

    private void startActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setFillAfter(true);
        welcome.startAnimation(alphaAnimation);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        startActivity();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }
}
