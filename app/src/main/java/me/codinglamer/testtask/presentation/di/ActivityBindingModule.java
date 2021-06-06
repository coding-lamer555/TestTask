package me.codinglamer.testtask.presentation.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.codinglamer.testtask.presentation.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentsBindingModule.class})
    public abstract MainActivity bindMainActivity();
}
