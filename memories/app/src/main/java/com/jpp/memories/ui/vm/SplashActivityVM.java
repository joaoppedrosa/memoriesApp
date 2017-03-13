package com.jpp.memories.ui.vm;

import android.content.res.Resources;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.jpp.memories.R;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class SplashActivityVM {

    public ObservableField<String> appName;
    public ObservableField<String> appSlogan;

    public SplashActivityVM(@NonNull Resources resources) {
        this.appName = new ObservableField<>(resources.getString(R.string.app_name));
        this.appSlogan = new ObservableField<>(resources.getString(R.string.slogan));
    }
}
