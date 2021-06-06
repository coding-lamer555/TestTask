package me.codinglamer.testtask.domain.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;

public interface GoodsRepository {

    Observable<List<GoodsPreviewEntity>> getGoodsList();
}
