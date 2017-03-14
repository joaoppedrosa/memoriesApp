package com.jpp.memories.ui;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.R;
import com.jpp.memories.databinding.ActivitySplashBinding;
import com.jpp.memories.ui.vm.SplashActivityVM;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    Resources resources;

    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoriesApplication.getApplicationComponent().inject(this);
        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        SplashActivityVM activitySplash = new SplashActivityVM(this.resources);
        activitySplashBinding.setSplashVM(activitySplash);
        this.navigator = new Navigator(this);
        init();
    }

    private void init() {
        int SPLASH_TIME = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigator.goToMainActivity();
                finish();
            }
        }, SPLASH_TIME);
    }
}
