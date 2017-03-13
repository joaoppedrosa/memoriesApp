package com.jpp.memories.core.dagger;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return this.application.getResources();
    }
}
