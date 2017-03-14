package com.jpp.memories.ui.vm;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;
import com.jpp.memories.api.APICallback;
import com.jpp.memories.core.MemoriesManager;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.model.Image;
import com.jpp.memories.model.Memories;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.adapter.MemoriesAdapter;
import com.jpp.memories.utils.PreferencesManager;

import java.util.List;

import static com.jpp.memories.utils.LogUtils.debug;
import static com.jpp.memories.utils.LogUtils.error;

/**
 * The type Main activity vm.
 *
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */
public class MainActivityVM extends BaseObservable implements APICallback<Memories> {

    private MemoriesManager memoriesManager;
    private MemoriesAdapter memoriesAdapter;
    private Navigator navigator;
    private IMainObserver mainObserver;
    public ObservableList<Memory> memoriesList;

    /**
     * Instantiates a new Main activity vm.
     *
     * @param navigator       the navigator
     * @param memoriesManager the memories manager
     */
    public MainActivityVM(@NonNull Navigator navigator, @NonNull MemoriesManager memoriesManager) {
        this.memoriesManager = memoriesManager;
        this.navigator = navigator;
        this.memoriesList = new ObservableArrayList<>();
        this.memoriesManager.getMemories(this);
    }

    private void setMemoriesList(List<Image> memoriesList) {
        for (Image image : memoriesList) {
            String img = image.getDisplaySizes().get(0).getUri();
            String quote = image.getTitle();
            this.memoriesList.add(new Memory(quote, img));
        }

        this.memoriesAdapter.notifyDataSetChanged();
        notifyPropertyChanged(BR.mainVM);
    }

    @Override
    public void onSuccess(Memories response) {
        debug("onSuccess");
        if (response == null) {
            return;
        }

        List<Image> imageList = response.getImages();
        if (imageList == null) {
            return;
        }

        if (imageList.isEmpty()) {
            return;
        }

        setMemoriesList(imageList);
    }

    @Override
    public void onError(Throwable throwable) {
        error(throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        this.mainObserver.onRequestComplete();
        debug("onComplete");
    }

    /**
     * Insert memories that are save localy
     */
    public void insertLocalMemories() {
        List<Memory> memoryList = this.memoriesManager.getMemories();
        if (memoryList == null) {
            return;
        }

        this.memoriesList.addAll(memoryList);
        if (this.memoriesAdapter != null) {
            this.memoriesAdapter.notifyDataSetChanged();
        }
        notifyPropertyChanged(BR.mainVM);
    }

    /**
     * Add memory.
     *
     * @param memory the memory
     */
    public void addMemory(Memory memory) {
        if (memory == null) {
            return;
        }

        this.memoriesManager.saveMemory(memory);
        this.memoriesList.add(0, memory);

        if (this.memoriesAdapter != null) {
            this.memoriesAdapter.notifyDataSetChanged();
        }
        notifyPropertyChanged(BR.mainVM);
    }

    /**
     * On create memories click.
     */
    public void onCreateMemoriesClick() {
        this.navigator.goToCreateMemoriesActivity();
    }

    /**
     * Gets memories list.
     *
     * @return the memories list
     */
    public ObservableList<Memory> getMemoriesList() {
        return memoriesList;
    }

    /**
     * Sets memories adapter.
     *
     * @param memoriesAdapter the memories adapter
     */
    public void setMemoriesAdapter(MemoriesAdapter memoriesAdapter) {
        this.memoriesAdapter = memoriesAdapter;
    }

    /**
     * Subscribe observer.
     *
     * @param mainObserver the main observer
     */
    public void subscribeObserver(IMainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }

    /**
     * The interface Main observer.
     */
    public interface IMainObserver {
        /**
         * On request complete.
         */
        void onRequestComplete();
    }
}
