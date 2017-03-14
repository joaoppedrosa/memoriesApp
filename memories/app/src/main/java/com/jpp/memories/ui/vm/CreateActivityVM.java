package com.jpp.memories.ui.vm;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.model.Memory;

/**
 *
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
public class CreateActivityVM {

    private ICreateObserver iCreateObserver;
    private Navigator navigator;
    private Gson gson;

    public ObservableField<String> quote;
    public ObservableField<String> image;

    /**
     * Instantiates a new Create activity vm.
     *
     * @param navigator the navigator
     * @param gson      the gson
     */
    public CreateActivityVM(@NonNull Navigator navigator, @NonNull Gson gson) {
        this.navigator = navigator;
        this.gson = gson;
        this.quote = new ObservableField<>();
        this.image = new ObservableField<>();
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
        if(this.navigator == null){
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
