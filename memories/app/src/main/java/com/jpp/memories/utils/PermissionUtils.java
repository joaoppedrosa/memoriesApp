package com.jpp.memories.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * The type Permission utils.
 *
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
public class PermissionUtils {

    /**
     * The constant PERMISSION_REQUEST_CODE.
     */
    public static final int PERMISSION_REQUEST_CODE = 5599;

    /**
     * Needs permissions boolean.
     *
     * @return the boolean
     */
    public static boolean needsPermissions() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    /**
     * Check if already have permission boolean.
     *
     * @param context    the context
     * @param permission the permission
     * @return the boolean
     */
    public static boolean checkPermission(@NonNull Context context, @NonNull String permission) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request camera permission.
     *
     * @param activity the activity
     */
    public static void requestCameraPermission(@NonNull Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }


    /**
     * Is all camera permission granted boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isAllCameraPermissionGranted(@NonNull Context context) {
        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        for (String permission : permissions) {
            if (!checkPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

}
