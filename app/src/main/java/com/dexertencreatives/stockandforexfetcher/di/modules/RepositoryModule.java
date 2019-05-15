package com.dexertencreatives.stockandforexfetcher.di.modules;

import android.app.Application;

import com.dexertencreatives.stockandforexfetcher.data.CoinTrackRepository;
import com.dexertencreatives.stockandforexfetcher.data.database.CoinTrackDao;
import com.dexertencreatives.stockandforexfetcher.data.network.CoinTrackRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 5/12/2019.
 */

@Module(includes = {AppModule.class, NetModule.class, DatabaseModule.class})
public class RepositoryModule {
    @Provides
    @Singleton
    CoinTrackRepository provideCoinTrackRepository(Application application, CoinTrackDao coinTrackDao, CoinTrackRemoteDataSource coinTrackRemoteDataSource) {
        return new CoinTrackRepository(application, coinTrackDao, coinTrackRemoteDataSource);
    }
}
