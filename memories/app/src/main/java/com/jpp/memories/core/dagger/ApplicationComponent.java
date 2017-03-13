package com.jpp.memories.core.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.jpp.memories.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SplashActivity activity);

    Context provideContext();

    Resources provideResources();

}