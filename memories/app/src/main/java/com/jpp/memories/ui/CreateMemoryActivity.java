package com.jpp.memories.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.R;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.databinding.ActivityCreateMemoryBinding;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.vm.CreateActivityVM;
import com.jpp.memories.utils.PermissionUtils;

import java.io.File;

import javax.inject.Inject;

public class CreateMemoryActivity extends AppCompatActivity implements CreateActivityVM.ICreateObserver {

    public static final String BUNDLE_MEMORY_CREATE = "create_memory";
    public static final int MEMORY_CREATE_REQUEST_CODE = 2233;
    private static final int CAMERA_PHOTO_INTENT = 1155;

    @Inject
    Gson gson;

    private Uri imageUri;
    private CreateActivityVM createActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoriesApplication.getApplicationComponent().inject(this);
        ActivityCreateMemoryBinding activityCreateMemoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_memory);
        this.createActivityVM = new CreateActivityVM(new Navigator(this), gson);
        this.createActivityVM.subscribeObserver(this);
        activityCreateMemoryBinding.setCreateVM(this.createActivityVM);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.create_memory);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PHOTO_INTENT && resultCode == Activity.RESULT_OK) {
            if (this.imageUri == null) {
                Toast.makeText(this, getString(R.string.image_error), Toast.LENGTH_LONG).show();
                return;
            }

            this.createActivityVM.setImage(this.imageUri.toString());

        } else {
            Toast.makeText(this, getString(R.string.image_error), Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onCapture() {
        if (PermissionUtils.needsPermissions()) {
            if (!PermissionUtils.isAllCameraPermissionGranted(this)) {
                PermissionUtils.requestCameraPermission(this);
                return;
            }
        }

        Intent chooserIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(Environment.getExternalStorageDirectory(), "image_" + System.currentTimeMillis() + ".jpg");
        chooserIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        this.imageUri = Uri.fromFile(f);
        startActivityForResult(chooserIntent, CAMERA_PHOTO_INTENT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.PERMISSION_REQUEST_CODE:
                if (!PermissionUtils.isAllCameraPermissionGranted(this)) {
                    PermissionUtils.requestCameraPermission(this);
                    return;
                }
                onCapture();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
