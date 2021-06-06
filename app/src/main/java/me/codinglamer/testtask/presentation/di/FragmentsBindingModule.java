package me.codinglamer.testtask.presentation.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.codinglamer.testtask.presentation.features.cart.CartFragment;
import me.codinglamer.testtask.presentation.features.home.HomeFragment;

@Module(includes = {ViewModelModule.class})
public abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    public abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    public abstract CartFragment bindCartFragment();
}
