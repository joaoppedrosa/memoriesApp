package com.jpp.memories.ui.vm;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpp.memories.R;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.model.Memory;

import java.lang.ref.WeakReference;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class DetailsActivityVM {

    private Resources resources;
    private Navigator navigator;

    public ObservableField<String> quote;
    public ObservableField<String> image;

    public DetailsActivityVM(@NonNull Resources resources, @NonNull Navigator navigator) {
        this.resources = resources;
        this.navigator = navigator;
        this.quote = new ObservableField<>();
        this.image = new ObservableField<>();
    }

    public void update(Memory memory) {
        this.quote.set(String.format(this.resources.getString(R.string.quote_style), memory.getQuote()));
        this.image.set(memory.getImage());
        this.quote.notifyChange();
        this.image.notifyChange();
    }

    public void onShareClick() {
        Uri uri;
        if (URLUtil.isValidUrl(this.image.get())) {
            uri = Uri.parse(this.image.get());
        } else {
            //from local storage
            uri = Uri.parse(this.image.get());
        }

        this.navigator.shareMemory(uri, this.quote.get());
    }
}
