package com.jpp.memories.core;

import android.support.annotation.NonNull;

import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.api.APICallback;
import com.jpp.memories.api.MemoriesAPI;
import com.jpp.memories.model.Memories;
import com.jpp.memories.model.Memory;
import com.jpp.memories.utils.Constants;
import com.jpp.memories.utils.PreferencesManager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * The type Memories manager.
 *
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */
public class MemoriesManager {

    private PreferencesManager preferencesManager;

    public MemoriesManager(@NonNull PreferencesManager preferencesManager){
        this.preferencesManager = preferencesManager;
    }

    /**
     * Get memories list
     *
     * @return the list
     */
    public List<Memory> getMemories(){
        return this.preferencesManager.getMemories();
    }

    /**
     * Save memory
     *
     * @param memory the memory
     */
    public void saveMemory(Memory memory){
        this.preferencesManager.insertMemory(memory);
    }

    /**
     * Gets memories.
     *
     * @param memoriesAPICallback the memories api callback
     */
    public void getMemories(final APICallback<Memories> memoriesAPICallback) {
        Observable<Memories> memoriesObservable = MemoriesAPI.getClient().getMemories(Constants.GETTY_IMAGE_API_KEY, Constants.QUERY_KEY);
        memoriesObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Memories>() {
                    @Override
                    public void onCompleted() {
                        memoriesAPICallback.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        memoriesAPICallback.onError(e);
                    }

                    @Override
                    public void onNext(Memories memories) {
                        memoriesAPICallback.onSuccess(memories);
                    }
                });
    }
}
