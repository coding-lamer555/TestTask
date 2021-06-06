package me.codinglamer.testtask.presentation.features.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;
import me.codinglamer.testtask.domain.usecase.GetGoodsListUseCase;
import me.codinglamer.testtask.presentation.rx.SchedulersProvider;

public class HomeViewModel extends ViewModel {

    private final GetGoodsListUseCase goodsListUseCase;
    private final SchedulersProvider schedulers;

    private final MutableLiveData<List<GoodsPreviewEntity>> goodsListLiveData = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomeViewModel(GetGoodsListUseCase goodsListUseCase, SchedulersProvider schedulers) {
        this.goodsListUseCase = goodsListUseCase;
        this.schedulers = schedulers;
    }

    public void getGoodsList() {
        Disposable subscribe = goodsListUseCase.execute()
                .subscribeOn(schedulers.io())
                .subscribe(goodsListLiveData::postValue,
                        throwable -> {
                        });
        compositeDisposable.add(subscribe);
    }

    public LiveData<List<GoodsPreviewEntity>> getGoodsListLiveData() {
        return goodsListLiveData;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
    }
}
