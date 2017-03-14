package com.jpp.memories.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.R;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.databinding.ActivityDetailsMemoryBinding;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.adapter.MemoriesAdapter;
import com.jpp.memories.ui.vm.DetailsActivityVM;

import javax.inject.Inject;

import static com.jpp.memories.utils.LogUtils.debug;

public class DetailsMemoryActivity extends AppCompatActivity {

    public static final String BUNDLE_MEMORY = "memory";

    @Inject
    Gson gson;
    @Inject
    Resources resources;

    private DetailsActivityVM detailsActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoriesApplication.getApplicationComponent().inject(this);
        ActivityDetailsMemoryBinding activityDetailsMemoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_memory);
        this.detailsActivityVM = new DetailsActivityVM(this.resources, new Navigator(this));
        activityDetailsMemoryBinding.setDetailsVM(this.detailsActivityVM);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.memory_details);
        }
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            finish();
            return;
        }

        String memory = bundle.getString(BUNDLE_MEMORY);
        if (memory == null) {
            finish();
            return;
        }

        Memory m = this.gson.fromJson(memory, Memory.class);
        this.detailsActivityVM.update(m);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
