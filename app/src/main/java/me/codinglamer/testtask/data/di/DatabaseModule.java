package me.codinglamer.testtask.data.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.codinglamer.testtask.data.datasource.local.db.AppDatabase;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "simple-goods-app-db")
                .build();
    }
}
