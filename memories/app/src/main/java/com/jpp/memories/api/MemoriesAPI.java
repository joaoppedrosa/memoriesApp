package com.jpp.memories.api;

import com.jpp.memories.MemoriesApplication;
import com.jpp.memories.model.Memories;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class MemoriesAPI {

    private static MemoriesAPInterface memoriesAPInterface;

    public static MemoriesAPInterface getClient() {
        if (memoriesAPInterface == null) {
            Retrofit retrofit = MemoriesApplication.getApplicationComponent().provideRetrofit();
            memoriesAPInterface = retrofit.create(MemoriesAPInterface.class);
        }
        return memoriesAPInterface;
    }

    public interface MemoriesAPInterface {
        @GET("/v3/search/images?fields=id,title,comp,thumb,referral_destinations&sort_order=best")
        Observable<Memories> getMemories(@Header("Api-Key") String apiKey, @Query("phrase") String key);
    }

}
