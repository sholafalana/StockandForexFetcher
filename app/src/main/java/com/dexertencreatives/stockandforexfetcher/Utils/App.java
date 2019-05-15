package com.dexertencreatives.stockandforexfetcher.Utils;

import android.app.Application;
import android.content.Context;

import com.dexertencreatives.stockandforexfetcher.BuildConfig;
import com.dexertencreatives.stockandforexfetcher.di.AppComponent;
import com.dexertencreatives.stockandforexfetcher.di.DaggerAppComponent;
import com.dexertencreatives.stockandforexfetcher.di.modules.AppModule;
import com.dexertencreatives.stockandforexfetcher.di.modules.NetModule;

/**
 * Created by USER on 5/12/2019.
 */

public class App extends Application {
    private AppComponent mAppComponent;
    private static Context mcontext;
    String CRYPTO_LIST = BuildConfig.CRYPTO_URL;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(CRYPTO_LIST))
                .build();
        mcontext = this;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getContext() {
        return mcontext;
    }
}
