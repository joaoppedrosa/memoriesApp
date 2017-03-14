package com.jpp.memories.core.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.jpp.memories.core.MemoriesManager;
import com.jpp.memories.ui.CreateMemoryActivity;
import com.jpp.memories.ui.DetailsMemoryActivity;
import com.jpp.memories.ui.MainActivity;
import com.jpp.memories.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(DetailsMemoryActivity activity);

    void inject(CreateMemoryActivity activity);

    Context provideContext();

    Resources provideResources();

    Retrofit provideRetrofit();

    Gson provideGSON();

    OkHttpClient provideOkHttpClient();

    MemoriesManager provideMemoriesManager();
}