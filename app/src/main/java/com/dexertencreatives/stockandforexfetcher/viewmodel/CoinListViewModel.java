package com.dexertencreatives.stockandforexfetcher.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.dexertencreatives.stockandforexfetcher.data.CoinTrackRepository;
import com.dexertencreatives.stockandforexfetcher.model.Coin;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by USER on 5/12/2019.
 */

public class CoinListViewModel extends ViewModel {
    private CoinTrackRepository mCoinTrackRepository;
    private LiveData<List<Coin>> coinListLiveData;

    @Inject
    public CoinListViewModel(CoinTrackRepository coinTrackRepository) {
        mCoinTrackRepository = coinTrackRepository;
        coinListLiveData = mCoinTrackRepository.getCoinData();
        refresh();
    }

    public LiveData<List<Coin>> getCoinListLiveData() {
        return coinListLiveData;
    }

    public void refresh() {
        mCoinTrackRepository.refreshCoinListData();
    }
}
