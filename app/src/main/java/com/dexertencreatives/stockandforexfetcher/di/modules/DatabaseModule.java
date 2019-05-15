package com.dexertencreatives.stockandforexfetcher.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.dexertencreatives.stockandforexfetcher.R;
import com.dexertencreatives.stockandforexfetcher.data.database.CoinTrackDao;
import com.dexertencreatives.stockandforexfetcher.data.database.CoinTrackDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 5/12/2019.
 */

@Module(includes = {AppModule.class})
public class DatabaseModule {

    @Provides
    @Singleton
    CoinTrackDatabase provideCoinTrackDatabase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                CoinTrackDatabase.class,
                application.getApplicationContext().getString(R.string.app_name))
                .build();
    }

    @Provides
    @Singleton
    CoinTrackDao provideCoinTrackDao(CoinTrackDatabase coinTrackDatabase) {
        return coinTrackDatabase.mCoinTrackDao();
    }
}
