package com.jpp.memories;

import android.app.Application;

import com.jpp.memories.core.dagger.ApplicationComponent;
import com.jpp.memories.core.dagger.ApplicationModule;
import com.jpp.memories.core.dagger.DaggerApplicationComponent;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class MemoriesApplication extends Application{

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule applicationModule = new ApplicationModule(this);
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .build();
        setApplicationComponent(applicationComponent);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
    public static void setApplicationComponent(ApplicationComponent appComp) {
        applicationComponent = appComp;
    }
}
