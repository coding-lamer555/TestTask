package me.codinglamer.testtask.domain.usecase;

import io.reactivex.rxjava3.core.Observable;

public interface ObservableUseCase<R> {

    Observable<R> execute();
}
