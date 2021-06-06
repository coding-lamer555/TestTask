package me.codinglamer.testtask;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import me.codinglamer.testtask.presentation.di.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
