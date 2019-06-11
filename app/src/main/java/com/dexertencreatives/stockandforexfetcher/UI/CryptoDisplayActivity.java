package com.dexertencreatives.stockandforexfetcher.UI;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.dexertencreatives.stockandforexfetcher.R;


import com.dexertencreatives.stockandforexfetcher.Utils.App;
import com.dexertencreatives.stockandforexfetcher.adapter.CryptoAdapter;
import com.dexertencreatives.stockandforexfetcher.model.Coin;
import com.dexertencreatives.stockandforexfetcher.viewmodel.CoinListViewModel;
import com.dexertencreatives.stockandforexfetcher.viewmodel.ViewModelFactory;
import com.dexertencreatives.stockandforexfetcher.widgets.AppWidget;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoDisplayActivity extends AppCompatActivity {

    public static final String MAIN_VIEW_TAG = CryptoDisplayActivity.class.getSimpleName();

    @Inject
    ViewModelFactory mFactory;
    private CoinListViewModel viewModel;
    @BindView(R.id.coinsRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private CryptoAdapter mCryptoAdapter;
    private final CharSequence toastString = " Coin Widget Set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ((App) getApplication()).getAppComponent().inject(this);
        viewModel = ViewModelProviders.of(this, mFactory).get(CoinListViewModel.class);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setOnRefreshListener(mRefreshListener);
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
        );
        viewModel.getCoinListLiveData().observe(this, new Observer<List<Coin>>() {
            @Override
            public void onChanged(@Nullable List<Coin> coins) {
                if (coins != null) {
                    if (mCryptoAdapter != null) {
                        mCryptoAdapter.updateCoinList(coins);
                    } else {
                        mCryptoAdapter = new CryptoAdapter(getApplicationContext(), coins);
                        mRecyclerView.setAdapter(mCryptoAdapter);
                    }
                }


            }
        });

    }

    // Menu Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.curr_link, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.currency:
                // Intent CurrIntent = new Intent(this, CurrencyDisplayActivity.class);
                //    startActivity(CurrIntent);
                return true;

            case R.id.refresh_settings:
                viewModel.refresh();
                return true;
            case R.id.widget_settings:
                createWidget();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createWidget() {

        String Tile1 = getString(R.string.rate_header);
        String Tile2 = getString(R.string.wid_msg);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.cuurency_widget_layout);
        ComponentName thisWidget = new ComponentName(this, AppWidget.class);
        remoteViews.setTextViewText(R.id.appwidget_head_text, Tile1);
        remoteViews.setTextViewText(R.id.appwidget_text, Tile2);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        Toast.makeText(this, toastString,
                Toast.LENGTH_LONG).show();
    }




    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (!mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            viewModel.refresh();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
