package me.codinglamer.testtask.data.di;

import dagger.Binds;
import dagger.Module;
import me.codinglamer.testtask.data.repository.GoodsRepositoryImpl;
import me.codinglamer.testtask.domain.repository.GoodsRepository;

@Module
public abstract class RepositoryBindingModule {

    @Binds
    public abstract GoodsRepository bindGoodsRepository(GoodsRepositoryImpl goodsRepository);
}
