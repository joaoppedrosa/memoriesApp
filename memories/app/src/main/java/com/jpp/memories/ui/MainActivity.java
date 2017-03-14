package com.jpp.memories.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.jpp.memories.BR;
import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.R;
import com.jpp.memories.core.MemoriesManager;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.databinding.ActivityMainBinding;
import com.jpp.memories.databinding.ContentMainBinding;
import com.jpp.memories.model.Memories;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.adapter.MemoriesAdapter;
import com.jpp.memories.ui.vm.CreateActivityVM;
import com.jpp.memories.ui.vm.MainActivityVM;
import com.jpp.memories.utils.InternetState;

import javax.inject.Inject;

import static com.jpp.memories.utils.LogUtils.debug;

public class MainActivity extends AppCompatActivity implements MemoriesAdapter.OnRecyclerViewItemClickListener, MainActivityVM.IMainObserver {

    @Inject
    Resources resources;
    @Inject
    MemoriesManager memoriesManager;
    @Inject
    Gson gson;

    private ActivityMainBinding activityMainBinding;
    private MainActivityVM mainActivityVM;
    private ContentMainBinding contentMainBinding;
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoriesApplication.getApplicationComponent().inject(this);
        this.activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.contentMainBinding = activityMainBinding.content;
        this.navigator = new Navigator(this);
        this.mainActivityVM = new MainActivityVM(this.navigator, this.memoriesManager);
        this.mainActivityVM.subscribeObserver(this);
        this.activityMainBinding.setMainVM(this.mainActivityVM);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!InternetState.isNetworkConnected(this)) {
            new MaterialDialog.Builder(this)
                    .title(R.string.internet_connection_title)
                    .content(R.string.internet_connection_text)
                    .contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
    }

    private void init() {
        setSupportActionBar(this.activityMainBinding.toolbar);
        this.contentMainBinding.recyclerView.setHasFixedSize(true);
        this.contentMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MemoriesAdapter memoriesAdapter = new MemoriesAdapter(this, this.mainActivityVM.getMemoriesList(), BR.rowVM);
        memoriesAdapter.setmOnRecyclerViewItemClickListener(this);
        this.contentMainBinding.recyclerView.setAdapter(memoriesAdapter);
        this.mainActivityVM.setMemoriesAdapter(memoriesAdapter);
        this.mainActivityVM.insertLocalMemories();
    }

    @Override
    public void onItemClick(Memory memory, int position) {
        if (memory == null) {
            return;
        }
        this.navigator.goToDetailsActivity(gson.toJson(memory));
    }

    @Override
    public void onRequestComplete() {
        this.contentMainBinding.progress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mainActivityVM != null) {
            this.mainActivityVM.subscribeObserver(null);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CreateMemoryActivity.MEMORY_CREATE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(CreateMemoryActivity.BUNDLE_MEMORY_CREATE);
                if (result == null) {
                    return;
                }
                Memory memory = this.gson.fromJson(result, Memory.class);
                this.mainActivityVM.addMemory(memory);
            }
        }
    }
}
