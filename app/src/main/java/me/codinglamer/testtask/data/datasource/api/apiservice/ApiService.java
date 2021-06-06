package me.codinglamer.testtask.data.datasource.api.apiservice;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import me.codinglamer.testtask.data.datasource.api.model.GoodsApiModel;
import retrofit2.http.GET;

public interface ApiService {

    @GET("getGoods.json")
    Single<List<GoodsApiModel>> getGoodsList();
}
