package me.codinglamer.testtask.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;
import me.codinglamer.testtask.domain.repository.GoodsRepository;

public class GetGoodsListUseCase implements ObservableUseCase<List<GoodsPreviewEntity>> {

    private final GoodsRepository goodsRepository;

    @Inject
    public GetGoodsListUseCase(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Observable<List<GoodsPreviewEntity>> execute() {
        return goodsRepository.getGoodsList();
    }
}
