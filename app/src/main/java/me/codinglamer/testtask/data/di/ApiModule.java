package me.codinglamer.testtask.data.di;

import dagger.Module;
import dagger.Provides;
import me.codinglamer.testtask.data.datasource.api.apiservice.ApiService;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    public ApiService bindApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
