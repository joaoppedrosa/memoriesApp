package com.jpp.memories.utils;

import android.util.Log;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class LogUtils {

    private static final String TAG = "MemoriesApp";

    /**
     * Debug.
     *
     * @param message the message
     */
    public static void debug(String message) {
        if (message == null) {
            return;
        }
        Log.d(TAG, message);
    }

    /**
     * Debug.
     *
     * @param message the message
     * @param args    the args
     */
    public static void debug(String message, Object... args) {
        if (message == null) {
            return;
        }
        Log.d(TAG, String.format(message, args));
    }

    /**
     * Debug.
     *
     * @param message the message
     * @param ex      the ex
     */
    public static void debug(String message, Throwable ex) {
        if (message == null) {
            return;
        }
        Log.d(TAG, message, ex);
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public static void info(String message) {
        if (message == null) {
            return;
        }
        Log.i(TAG, message);
    }

    /**
     * Info.
     *
     * @param message the message
     * @param ex      the ex
     */
    public static void info(String message, Throwable ex) {
        if (message == null) {
            return;
        }
        Log.i(TAG, message, ex);
    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public static void warning(String message) {
        if (message == null) {
            return;
        }
        Log.w(TAG, message);
    }

    /**
     * Warning.
     *
     * @param message the message
     * @param args    the args
     */
    public static void warning(String message, Object... args) {
        if (message == null) {
            return;
        }
        Log.w(TAG, String.format(message, args));
    }

    /**
     * Warning.
     *
     * @param message the message
     * @param ex      the ex
     */
    public static void warning(String message, Throwable ex) {
        if (message == null) {
            return;
        }
        Log.w(TAG, message, ex);
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public static void error(String message) {
        if (message == null) {
            return;
        }
        Log.e(TAG, message);
    }

    /**
     * Error.
     *
     * @param message the message
     * @param ex      the ex
     */
    public static void error(String message, Throwable ex) {
        if (message == null) {
            return;
        }
        Log.e(TAG, message, ex);
    }
}
