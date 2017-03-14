package com.jpp.memories.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * The type Internet state.
 *
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
public class InternetState {

    /**
     * Is network connected boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
