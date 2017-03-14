package com.jpp.memories.ui.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jpp.memories.R;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.model.Memory;

import static com.jpp.memories.utils.StringsUtils.isNullOrEmpty;

/**
 * The type Create activity vm.
 *
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
public class CreateActivityVM {

    private ICreateObserver iCreateObserver;
    private Navigator navigator;
    private Gson gson;
    private Context context;

    private ObservableField<Uri> imageUri;
    public ObservableField<String> quote;
    public ObservableField<String> image;

    /**
     * Instantiates a new Create activity vm.
     *
     * @param navigator the navigator
     * @param gson      the gson
     */
    public CreateActivityVM(@NonNull Context context, @NonNull Navigator navigator, @NonNull Gson gson) {
        this.context = context;
        this.navigator = navigator;
        this.gson = gson;
        this.imageUri = new ObservableField<>();
        this.quote = new ObservableField<>();
        this.image = new ObservableField<>();
    }

    /**
     * Set image uri.
     *
     * @param imageUri the image uri
     */
    public void setImageUri(Uri imageUri) {
        this.imageUri.set(imageUri);
        this.imageUri.notifyChange();
    }

    /**
     * Gets image uri.
     *
     * @return the image uri
     */
    public Uri getImageUri() {
        return imageUri.get();
    }

    /**
     * Sets image
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image.set(image);
        this.image.notifyChange();
    }

    /**
     * Subscribe observer
     *
     * @param iCreateObserver the create observer
     */
    public void subscribeObserver(ICreateObserver iCreateObserver) {
        this.iCreateObserver = iCreateObserver;
    }

    /**
     * On capture image
     */
    public void onCapture() {
        if (this.iCreateObserver == null) {
            return;
        }

        this.iCreateObserver.onCapture();
    }

    /**
     * On save memory
     */
    public void onSave() {
        if (this.navigator == null) {
            return;
        }

        if (isNullOrEmpty(this.image.get())) {
            Toast.makeText(this.context, R.string.error_no_picture, Toast.LENGTH_SHORT).show();
            return;
        }

        if (isNullOrEmpty(this.quote.get())) {
            Toast.makeText(this.context, R.string.error_no_quote, Toast.LENGTH_SHORT).show();
            return;
        }

        Memory memory = new Memory();
        memory.setImage(this.image.get());
        memory.setQuote(this.quote.get());

        this.navigator.goToMainWithResult(this.gson.toJson(memory));
    }


    /**
     * The interface observer
     */
    public interface ICreateObserver {
        /**
         * On capture image
         */
        void onCapture();
    }
}
