package com.example.appclock.ui.act.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.appclock.R;
import com.example.appclock.ui.act.account.AccountAct;
import com.example.appclock.ui.act.main.MainAct;

public class SplashAct extends AppCompatActivity {
    private static final long TIME_DELAY = 4000L;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        initViews();
        initEvents();
    }

    private void initEvents() {

    }

    private void initViews() {
        lottieAnimationView = findViewById(R.id.lotte_animation_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashAct.this, AccountAct.class);
                startActivity(intent);
                finish();
            }
        },TIME_DELAY);
    }
}
