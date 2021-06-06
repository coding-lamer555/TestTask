package me.codinglamer.testtask.data.datasource.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import me.codinglamer.testtask.data.datasource.local.model.GoodsLocalModel;

@Dao
public interface GoodsDao {

    @Query("SELECT * FROM goods")
    Observable<List<GoodsLocalModel>> getAll();

    @Query("SELECT * FROM goods WHERE countInCart > 0")
    Observable<List<GoodsLocalModel>> getAllInCart();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GoodsLocalModel> goodsList);

    @Delete
    void delete(GoodsLocalModel goods);
}
