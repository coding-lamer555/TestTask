package me.codinglamer.testtask.data.repository;

import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.codinglamer.testtask.data.datasource.api.apiservice.ApiService;
import me.codinglamer.testtask.data.datasource.api.model.GoodsApiModel;
import me.codinglamer.testtask.data.datasource.local.db.dao.GoodsDao;
import me.codinglamer.testtask.data.datasource.local.model.GoodsLocalModel;
import me.codinglamer.testtask.data.mapper.GoodsMapper;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;
import me.codinglamer.testtask.domain.repository.GoodsRepository;

public class GoodsRepositoryImpl implements GoodsRepository {

    private final String TAG = GoodsRepositoryImpl.class.getSimpleName();

    private final ApiService apiService;
    private final GoodsDao goodsDao;
    private final Lazy<GoodsMapper> goodsPreviewMapper;

    @Inject
    public GoodsRepositoryImpl(ApiService apiService, GoodsDao goodsDao, Lazy<GoodsMapper> goodsPreviewMapper) {
        this.apiService = apiService;
        this.goodsDao = goodsDao;
        this.goodsPreviewMapper = goodsPreviewMapper;
    }

    @Override
    public Observable<List<GoodsPreviewEntity>> getGoodsList() {
        Observable<List<GoodsLocalModel>> goodsLocal = goodsDao.getAll();
//        return goodsLocal.flatMap(localResult -> {
//            if (!localResult.isEmpty()) {
//                return goodsLocal.map(list -> list.stream().map(goodsPreviewMapper.get()::mapLocalToEntity))
//                        .collect(Collectors.toList());
//            } else {
//                return Observable.just(localResult);
//            }
//        });
//        return goodsLocal.map(list -> list.stream()
//                .map(goodsPreviewMapper.get()::mapLocalToEntity)
//                .collect(Collectors.toList()));

//        Single<List<GoodsApiModel>> goodsApi = apiService.getGoodsList();

//        goodsApi.subscribeOn(Schedulers.io())
//                .subscribe((goodsApiModels, throwable) -> cacheGoodsApi(goodsApiModels))
//                .dispose();

        Log.e(TAG, "Start of the empty check");
        goodsLocal.subscribeOn(Schedulers.io())
                .subscribe(goodsLocalModels -> {
                            if (goodsLocalModels.isEmpty()) {
                                Log.e(TAG, "Empty");
                                apiService.getGoodsList()
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(goodsApiModels -> {
                                            Log.e(TAG, "Got it");
                                            cacheGoodsApi(goodsApiModels);
                                        }, throwable -> {
                                        });
                            } else {
                                Log.e(TAG, "Not empty");
                            }
                        },
                        throwable -> {
                        });

        return goodsLocal.map(list -> list.stream()
                .map(goodsPreviewMapper.get()::mapLocalToEntity)
                .collect(Collectors.toList()));

//        goodsApi.blockingGet();
//        return null;

//        return goodsLocal.flatMap(localResult -> {
//                    if (!localResult.isEmpty()) {
//                        return Observable.just(localResult.stream()
//                                .map(goodsPreviewMapper.get()::mapLocalToEntity)
//                                .collect(Collectors.toList()));
//                    } else {
//                        return Observable.just(localResult);
//                    }
//                });
//
//        List<GoodsLocalModel> goodsLocalModels = goodsLocal.subscribe();
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (!goodsLocalModels.isEmpty()) {
//            return mapLocalObservableToEntityObservable(goodsLocal);
//        } else {
//            Single<List<GoodsApiModel>> goodsApi = apiService.getGoodsList();
//
//            goodsApi.blockingSubscribe(new BiConsumerSingleObserver<>((goodsApiModels, throwable) -> cacheGoodsApi(goodsApiModels)));
//
//            return mapLocalObservableToEntityObservable(goodsDao.getAll());
//        }
//        return mapLocalObservableToEntityObservable(goodsLocal);
    }

    /*private Observable<List<GoodsPreviewEntity>> mapLocalObservableToEntityObservable(Observable<List<GoodsLocalModel>> goodsLocal) {
        return goodsLocal.map(list -> list.stream()
                .map(goodsPreviewMapper.get()::mapLocalToEntity)
                .collect(Collectors.toList()));
    }*/

    private void cacheGoodsApi(List<GoodsApiModel> goodsApiModels) {
        goodsDao.insertAll(goodsApiModels.stream()
                .map(goodsPreviewMapper.get()::mapApiToLocal)
                .collect(Collectors.toList())
        );
    }
}
