package me.codinglamer.testtask.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.internal.observers.BiConsumerSingleObserver;
import me.codinglamer.testtask.data.datasource.api.apiservice.ApiService;
import me.codinglamer.testtask.data.datasource.api.model.GoodsApiModel;
import me.codinglamer.testtask.data.datasource.local.db.dao.GoodsDao;
import me.codinglamer.testtask.data.datasource.local.model.GoodsLocalModel;
import me.codinglamer.testtask.data.mapper.GoodsPreviewMapper;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;
import me.codinglamer.testtask.domain.repository.GoodsRepository;

public class GoodsRepositoryImpl implements GoodsRepository {

    private final ApiService apiService;
    private final GoodsDao goodsDao;
    private final Lazy<GoodsPreviewMapper> goodsPreviewMapper;

    @Inject
    public GoodsRepositoryImpl(ApiService apiService, GoodsDao goodsDao, Lazy<GoodsPreviewMapper> goodsPreviewMapper) {
        this.apiService = apiService;
        this.goodsDao = goodsDao;
        this.goodsPreviewMapper = goodsPreviewMapper;
    }

    @Override
    public Observable<List<GoodsPreviewEntity>> getGoodsList() {
        Observable<List<GoodsLocalModel>> goodsLocal = goodsDao.getAll();
//        return goodsLocal.flatMap(localResult -> {
//                    if (!localResult.isEmpty()) {
//                        return Observable.just(localResult.stream()
//                                .map(goodsPreviewMapper.get()::mapLocalToEntity)
//                                .collect(Collectors.toList()));
//                    } else {
//                        return Observable.just(localResult);
//                    }
//                });

        List<GoodsLocalModel> goodsLocalModels = goodsLocal.blockingLast();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if (!goodsLocalModels.isEmpty()) {
//            return mapLocalObservableToEntityObservable(goodsLocal);
//        } else {
//            Single<List<GoodsApiModel>> goodsApi = apiService.getGoodsList();
//
//            goodsApi.blockingSubscribe(new BiConsumerSingleObserver<>((goodsApiModels, throwable) -> cacheGoodsApi(goodsApiModels)));
//
//            return mapLocalObservableToEntityObservable(goodsDao.getAll());
//        }
        return mapLocalObservableToEntityObservable(goodsLocal);
    }

    private Observable<List<GoodsPreviewEntity>> mapLocalObservableToEntityObservable(Observable<List<GoodsLocalModel>> goodsLocal) {
        return goodsLocal.map(list -> list.stream()
                .map(goodsPreviewMapper.get()::mapLocalToEntity)
                .collect(Collectors.toList()));
    }

    private void cacheGoodsApi(List<GoodsApiModel> goodsApiModels) {
        goodsDao.insertAll(goodsApiModels.stream()
                .map(goodsPreviewMapper.get()::mapApiToLocal)
                .collect(Collectors.toList())
        );
    }
}
