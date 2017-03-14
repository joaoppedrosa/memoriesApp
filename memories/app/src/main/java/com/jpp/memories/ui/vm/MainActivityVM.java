package com.jpp.memories.ui.vm;

import android.content.res.Resources;
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

import java.util.List;

import static com.jpp.memories.utils.LogUtils.debug;
import static com.jpp.memories.utils.LogUtils.error;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class MainActivityVM extends BaseObservable implements APICallback<Memories> {

    private MemoriesAdapter memoriesAdapter;
    private Navigator navigator;
    private MemoriesManager memoriesManager;
    private Resources resources;
    private IMainObserver mainObserver;
    private ObservableList<Memory> memoriesList;

    public MainActivityVM(@NonNull Navigator navigator, @NonNull Resources resources, @NonNull MemoriesManager memoriesManager) {
        this.navigator = navigator;
        this.resources = resources;
        this.memoriesManager = memoriesManager;
        this.memoriesList = new ObservableArrayList<>();
        this.memoriesManager.getMemories(this);
    }

    public void addMemory(Memory memory) {
        if (memory == null) {
            return;
        }

        this.memoriesList.add(0, memory);
        this.memoriesAdapter.notifyDataSetChanged();
        notifyPropertyChanged(BR.mainVM);
    }

    public void onCreateMemoriesClick() {
        this.navigator.goToCreateMemoriesActivity();
    }

    public void clearList() {
        this.memoriesList.clear();
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

    public ObservableList<Memory> getMemoriesList() {
        return memoriesList;
    }

    public void setMemoriesAdapter(MemoriesAdapter memoriesAdapter) {
        this.memoriesAdapter = memoriesAdapter;
    }

    public void subscribeObserver(IMainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }

    public interface IMainObserver {
        void onRequestComplete();
    }
}
