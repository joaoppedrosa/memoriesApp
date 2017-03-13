package com.jpp.memories.ui;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.R;
import com.jpp.memories.core.MemoriesManager;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.databinding.ActivityMainBinding;
import com.jpp.memories.databinding.ContentMainBinding;
import com.jpp.memories.model.Memories;
import com.jpp.memories.ui.vm.MainActivityVM;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Resources resources;
    @Inject
    MemoriesManager memoriesManager;

    private ActivityMainBinding activityMainBinding;
    private MainActivityVM mainActivityVM;
    private Navigator navigator;
    private ContentMainBinding contentMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoriesApplication.getApplicationComponent().inject(this);
        this.activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.contentMainBinding = activityMainBinding.content;
        this.navigator = new Navigator(this);
        this.mainActivityVM = new MainActivityVM(this.navigator, this.resources, this.memoriesManager);
        this.activityMainBinding.setMainVM(this.mainActivityVM);
        init();
    }

    private void init() {
        setSupportActionBar(this.activityMainBinding.toolbar);
    }
}
