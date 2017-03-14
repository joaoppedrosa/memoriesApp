package com.jpp.memories.core;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jpp.memories.R;
import com.jpp.memories.ui.DetailsMemoryActivity;
import com.jpp.memories.ui.MainActivity;
import com.jpp.memories.ui.CreateMemoryActivity;
import com.jpp.memories.ui.vm.DetailsActivityVM;

import java.lang.ref.WeakReference;

/**
 *
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */
public class Navigator {

    private WeakReference<Activity> activityWeakReference;

    /**
     * Instantiates a new Navigator.
     *
     * @param activity the activity
     */
    public Navigator(@NonNull Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    /**
     * Go to main activity.
     */
    public void goToMainActivity() {
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Go to create memories activity.
     */
    public void goToCreateMemoriesActivity() {
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity, CreateMemoryActivity.class);
        startActivityForResult(activity, intent, CreateMemoryActivity.MEMORY_CREATE_REQUEST_CODE);
    }

    /**
     * Go to details activity.
     *
     * @param content the content
     */
    public void goToDetailsActivity(@NonNull String content) {
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity, DetailsMemoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetailsMemoryActivity.BUNDLE_MEMORY, content);
        intent.putExtras(bundle);
        startActivity(activity, intent);
    }

    /**
     * Go to main with result.
     *
     * @param result the result
     */
    public void goToMainWithResult(@NonNull String result){
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(CreateMemoryActivity.BUNDLE_MEMORY_CREATE,result);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    /**
     * Share memory.
     *
     * @param image the image
     * @param text  the text
     */
    public void shareMemory(@NonNull Uri image, @NonNull String text) {
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_STREAM, image);
        intent.setType("image/*");
        activity.startActivity(intent);
    }

    private void startActivity(@NonNull Activity activity, @NonNull Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
