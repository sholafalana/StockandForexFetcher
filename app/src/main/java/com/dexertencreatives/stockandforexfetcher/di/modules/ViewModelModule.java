package com.dexertencreatives.stockandforexfetcher.di.modules;

import android.arch.lifecycle.ViewModel;

import com.dexertencreatives.stockandforexfetcher.viewmodel.CoinListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by USER on 5/12/2019.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel.class)
    abstract ViewModel coinListViewModel(CoinListViewModel coinListViewModel);


}
