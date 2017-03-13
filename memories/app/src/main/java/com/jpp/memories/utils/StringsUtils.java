package com.jpp.memories.utils;

import android.support.annotation.Nullable;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class StringsUtils {

    /**
     * @param string The string to be checked.
     * @return {@code true} if the given string is null or is empty.
     */
    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

}
