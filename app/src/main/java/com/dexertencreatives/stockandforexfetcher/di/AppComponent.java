package com.dexertencreatives.stockandforexfetcher.di;

import com.dexertencreatives.stockandforexfetcher.data.network.CoinTrackIntentService;
import com.dexertencreatives.stockandforexfetcher.di.modules.AppModule;
import com.dexertencreatives.stockandforexfetcher.di.modules.DatabaseModule;
import com.dexertencreatives.stockandforexfetcher.di.modules.NetModule;
import com.dexertencreatives.stockandforexfetcher.di.modules.RepositoryModule;
import com.dexertencreatives.stockandforexfetcher.di.modules.ViewModelModule;
import com.dexertencreatives.stockandforexfetcher.UI.CoinListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by USER on 5/12/2019.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        ViewModelModule.class,
        RepositoryModule.class,
        DatabaseModule.class,
})

public interface AppComponent {
    void inject(CoinTrackIntentService coinTrackIntentService);

    void inject(CoinListActivity coinListActivity);
}
