package com.jpp.memories.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.model.Memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */

public class PreferencesManager {

    private static final String PREFERENCES_NAME = "com.jpp.memories";
    private static final String MEMORIES_KEY_VALUE = "memories";

    private final SharedPreferences mPref;
    private final Gson gson;

    public PreferencesManager(@NonNull Context context, @NonNull Gson gson) {
        this.gson = gson;
        this.mPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public List<Memory> getMemories() {
        String memories = this.mPref.getString(MEMORIES_KEY_VALUE, null);
        if (memories == null) {
            return null;
        }

        return this.gson.fromJson(memories, new TypeToken<List<Memory>>() {
        }.getType());
    }

    public void insertMemory(Memory memory) {
        List<Memory> memoryList = getMemories();
        if (memoryList == null) {
            memoryList = new ArrayList<>();
        }

        memoryList.add(memory);
        this.mPref.edit()
                .putString(MEMORIES_KEY_VALUE, this.gson.toJson(memoryList))
                .apply();
    }

    public boolean clear() {
        return this.mPref.edit()
                .clear()
                .commit();
    }
}
