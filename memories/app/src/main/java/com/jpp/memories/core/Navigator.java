package com.jpp.memories.core;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.jpp.memories.R;
import com.jpp.memories.ui.MainActivity;
import com.jpp.memories.ui.CreateMemoryActivity;

import java.lang.ref.WeakReference;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class Navigator {

    private WeakReference<Activity> activityWeakReference;

    public Navigator(@NonNull Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

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

    public void goToCreateMemoriesActivity() {
        if (this.activityWeakReference == null) {
            return;
        }

        Activity activity = this.activityWeakReference.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity, CreateMemoryActivity.class);
        startActivity(activity, intent);
    }

    public void startActivity(@NonNull Activity activity, @NonNull Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
