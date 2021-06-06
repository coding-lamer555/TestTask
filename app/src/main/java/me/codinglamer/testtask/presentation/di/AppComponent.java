package me.codinglamer.testtask.presentation.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.codinglamer.testtask.App;
import me.codinglamer.testtask.data.di.ApiModule;
import me.codinglamer.testtask.data.di.DaoModule;
import me.codinglamer.testtask.data.di.DatabaseModule;
import me.codinglamer.testtask.data.di.NetworkModule;
import me.codinglamer.testtask.data.di.RepositoryBindingModule;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ApiModule.class,
        DatabaseModule.class,
        DaoModule.class,
        RepositoryBindingModule.class,
        AppModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        FragmentsBindingModule.class,
        ActivityBindingModule.class}
)
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
