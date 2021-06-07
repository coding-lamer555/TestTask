package me.codinglamer.testtask.data.mapper;

import java.util.Locale;

import javax.inject.Inject;

import me.codinglamer.testtask.core.BaseMapper;
import me.codinglamer.testtask.data.datasource.api.model.GoodsApiModel;
import me.codinglamer.testtask.data.datasource.local.model.GoodsLocalModel;
import me.codinglamer.testtask.domain.model.GoodsPreviewEntity;

public class GoodsMapper implements BaseMapper<GoodsApiModel, GoodsPreviewEntity, GoodsLocalModel> {

    @Inject
    public GoodsMapper() {

    }

    @Override
    public GoodsPreviewEntity mapApiToEntity(GoodsApiModel goodsApiModel) {
        return null;
    }

    @Override
    public GoodsLocalModel mapApiToLocal(GoodsApiModel goodsApiModel) {
        return GoodsLocalModel.builder()
                .id(goodsApiModel.getId())
                .name(goodsApiModel.getName())
                .description(goodsApiModel.getDescription())
                .preview(goodsApiModel.getPreview())
                .gallery(goodsApiModel.getGallery())
                .price(goodsApiModel.getPrice())
                .rating(goodsApiModel.getRating())
                .weight(goodsApiModel.getWeight())
                .countInCart(0)
                .build();
    }

    @Override
    public GoodsPreviewEntity mapLocalToEntity(GoodsLocalModel goodsLocalModel) {
        return GoodsPreviewEntity.builder()
                .id(goodsLocalModel.id)
                .preview(goodsLocalModel.preview)
                .price(String.format(Locale.US, "$ %f", goodsLocalModel.price))
                .rating(String.format(Locale.US, "%f", goodsLocalModel.rating))
                .name(goodsLocalModel.name)
                .countInCart(goodsLocalModel.countInCart)
                .build();
    }
}
