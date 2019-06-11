package com.dexertencreatives.stockandforexfetcher.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dexertencreatives.stockandforexfetcher.R;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {
    @BindView(R.id.market_rate)
    Button btMarketRate;
    @BindView(R.id.pivot_generator)
    Button btSignalGen;
    @BindView(R.id.trade_journal)
    Button mTradeJournal;
    @BindView(R.id.trade_resource)
    Button mTradeResource;

    @BindView(R.id.fab_search)
    FloatingActionButton floatingActionButton;


    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.search_navigation, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(R.string.bar_main_title);

        ImageButton imageButtonAdd = (ImageButton) mCustomView
                .findViewById(R.id.imageAdd);



        btMarketRate.setOnClickListener(v -> {
            Intent CurrDetailIntent = new Intent(v.getContext(), CryptoDisplayActivity.class);
            startActivity(CurrDetailIntent);
            Bundle bundle = new Bundle();

            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, getString(R.string.button_one));
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.Curr_display));
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, getString(R.string.Button_click));
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        });

        btSignalGen.setOnClickListener(v -> {
            Intent pivotIntent = new Intent(v.getContext(), PivotGeneratorActivity.class);
            startActivity(pivotIntent);
        });
        mTradeResource.setOnClickListener(v -> {
            Intent currIntent = new Intent(v.getContext(), TradeResourceActivity.class);
            startActivity(currIntent);
        });


        mTradeJournal.setOnClickListener(v -> {
            Intent tjIntent = new Intent(v.getContext(), TradeJournalActivity.class);
            startActivity(tjIntent);
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(HomePageActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        HomePageActivity.this);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String sym = userInput.getText().toString().trim();
                                        Intent graphIntent = new Intent(v.getContext(), GraphActivity.class);
                                        graphIntent.putExtra(GraphActivity.EXTRA_SYMBOL, sym);
                                        startActivity(graphIntent);

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(HomePageActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        HomePageActivity.this);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String sym = userInput.getText().toString().trim();

                                        Intent graphIntent2 = new Intent(view.getContext(), GraphActivity.class);
                                        graphIntent2.putExtra(GraphActivity.EXTRA_SYMBOL, sym);
                                        startActivity(graphIntent2);

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Live_rate:
                Intent CurrDetailIntent = new Intent(HomePageActivity.this, CryptoDisplayActivity.class);
                startActivity(CurrDetailIntent);

                return true;
            case R.id.pivot_gen:
                Intent pivotIntent = new Intent(HomePageActivity.this, PivotGeneratorActivity.class);
                startActivity(pivotIntent);
                return true;

            case R.id.trade_journal:
                Intent journalIntent = new Intent(HomePageActivity.this, TradeJournalActivity.class);
                startActivity(journalIntent);

                return true;
            case R.id.trade_resources:
                Intent trIntent = new Intent(HomePageActivity.this, TradeResourceActivity.class);
                startActivity(trIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
