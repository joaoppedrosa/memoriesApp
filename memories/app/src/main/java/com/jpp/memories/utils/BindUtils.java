package com.jpp.memories.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpp.memories.R;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.vm.MainActivityVM;

import java.util.List;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */

public class BindUtils {

    @BindingAdapter("android:listVisibility")
    public static void setVisibility(View view, List<Memory> memories) {
        if (memories == null) {
            return;
        }

        view.setVisibility(memories.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, String image) {
        if (image == null) {
            return;
        }

        Glide.with(imageView.getContext())
                .load(image)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imageView);
    }
}
