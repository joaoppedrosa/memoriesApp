package com.jpp.memories.ui.vm;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpp.memories.R;
import com.jpp.memories.model.Memory;

/**
 *
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */
public class ItemMemoryVM {

    public ObservableField<String> quote;
    public ObservableField<String> image;

    /**
     * Instantiates a new Item memory vm.
     *
     * @param memory    the memory
     * @param resources the resources
     */
    public ItemMemoryVM(@NonNull Memory memory, @NonNull Resources resources) {
        this.quote = new ObservableField<>(String.format(resources.getString(R.string.quote_style), memory.getQuote()));
        this.image = new ObservableField<>(memory.getImage());
        this.quote.notifyChange();
        this.image.notifyChange();
    }

    /**
     * Sets image view resource.
     *
     * @param imageView the image view
     * @param image     the image
     */
    @BindingAdapter({"android:url"})
    public static void setImageViewResource(ImageView imageView, String image) {
        Glide.with(imageView.getContext())
                .load(image)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);
    }
}
