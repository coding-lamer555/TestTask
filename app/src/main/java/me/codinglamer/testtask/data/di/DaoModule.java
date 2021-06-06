package me.codinglamer.testtask.data.di;

import dagger.Module;
import dagger.Provides;
import me.codinglamer.testtask.data.datasource.local.db.AppDatabase;
import me.codinglamer.testtask.data.datasource.local.db.dao.GoodsDao;

@Module(includes = {DatabaseModule.class})
public class DaoModule {

    @Provides
    public GoodsDao provideGoodsDao(AppDatabase database) {
        return database.goodsDao();
    }
}
