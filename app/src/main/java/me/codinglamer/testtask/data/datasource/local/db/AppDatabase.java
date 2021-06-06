package me.codinglamer.testtask.data.datasource.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.codinglamer.testtask.data.datasource.local.db.dao.GoodsDao;
import me.codinglamer.testtask.data.datasource.local.model.GoodsLocalModel;

@Database(version = 1, entities = {GoodsLocalModel.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract GoodsDao goodsDao();
}
