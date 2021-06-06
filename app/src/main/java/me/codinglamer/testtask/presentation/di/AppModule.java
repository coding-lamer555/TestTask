package me.codinglamer.testtask.presentation.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import me.codinglamer.testtask.presentation.rx.SchedulersFacade;
import me.codinglamer.testtask.presentation.rx.SchedulersProvider;

@Module
public abstract class AppModule {

    @Binds
    public abstract Context bindContext(Application application);

    @Binds
    public abstract SchedulersProvider providerScheduler(SchedulersFacade schedulersFacade);
}
