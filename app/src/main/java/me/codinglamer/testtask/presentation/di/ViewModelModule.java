package me.codinglamer.testtask.presentation.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.codinglamer.testtask.presentation.features.cart.CartViewModel;
import me.codinglamer.testtask.presentation.features.home.HomeViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    public abstract ViewModel bindCartViewModel(CartViewModel viewModel);
}
