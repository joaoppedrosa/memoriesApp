package com.jpp.memories.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpp.memories.R;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */

public class BindUtils {

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
