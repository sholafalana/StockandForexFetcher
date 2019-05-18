package com.dexertencreatives.stockandforexfetcher.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dexertencreatives.stockandforexfetcher.R;
import com.dexertencreatives.stockandforexfetcher.adapter.AlphaDataAdapter;
import com.dexertencreatives.stockandforexfetcher.data.network.Formatter;
import com.dexertencreatives.stockandforexfetcher.data.network.NetworkSingleton;
import com.dexertencreatives.stockandforexfetcher.data.network.NetworkURLRequest;
import com.dexertencreatives.stockandforexfetcher.model.ParseAlphaData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.volley.Request.Method.GET;
import static com.dexertencreatives.stockandforexfetcher.UI.GraphActivity.EXTRA_SYMBOL;

public class CurrencyExchangeActivity extends AppCompatActivity {
    private static final String TAG = CurrencyExchangeActivity.class.getSimpleName();
    @BindView(R.id.tvoutputRate)
    TextView tvOutputRate;
    @BindView(R.id.etInput)
    TextInputEditText etInput;
    @BindView(R.id.base_unit)
    TextInputEditText tvBasetext;
    @BindView(R.id.home_unit)
    TextInputEditText tvHomeText;
    @BindView(R.id.set_rate)
    TextInputEditText tvSetRate;
    @BindView(R.id.fetch_rate)
    Button btnFetchRate;
    @BindView(R.id.btnCalculate)
    Button btnCalculate;
    @BindView(R.id.progressBar_curr)
    ProgressBar progressBar;
    private String symbol, FromSymbol, ToSymbol;
    private static final String SAVED_SORT = "KeyForLayoutManagerState";

    private String fullSymbol, baseSymbol, homeSymbol, tvQuantity, price;
    private double currencyRate;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);
        ButterKnife.bind(this);

        setTitle(R.string.cuur_ex_title);
        mRequestQueue = NetworkSingleton.getInstance(this).getRequestQueue();
        NetworkURLRequest networkURLRequest = new NetworkURLRequest();
        Intent i1 = getIntent();
        if (i1 != null) {
            symbol = i1.getStringExtra(EXTRA_SYMBOL);
            if (symbol != null) {
                if (symbol.length() == 6) {
                    char firstLetter = symbol.charAt(0);
                    char secondLetter = symbol.charAt(1);
                    char thirdLetter = symbol.charAt(2);
                    char tofirstLetter = symbol.charAt(3);
                    char tosecondLetter = symbol.charAt(4);
                    char tothirdLetter = symbol.charAt(5);
                    FromSymbol =
                            Character.toString(firstLetter)
                                    + Character.toString(secondLetter)
                                    + Character.toString(thirdLetter);
                    ToSymbol =
                            Character.toString(tofirstLetter)
                                    + Character.toString(tosecondLetter)
                                    + Character.toString(tothirdLetter);
                    tvBasetext.setText(FromSymbol);

                    tvHomeText.setText(ToSymbol);
                    if (isOnline()) {
                        baseSymbol = tvBasetext.getText().toString().trim();
                        homeSymbol = tvHomeText.getText().toString().trim();
                        fullSymbol = baseSymbol + homeSymbol;
                        fetchCurrData(networkURLRequest.Get1ForgeApiRate1(fullSymbol));
                    } else {
                        showErrorMessage();
                    }
                }
            }
        }


        btnCalculate.setOnClickListener(view -> {
            baseSymbol = tvBasetext.getText().toString().trim();
            homeSymbol = tvHomeText.getText().toString().trim();
            tvQuantity = etInput.getText().toString().trim();
            currencyRate = Double.parseDouble(tvSetRate.getText().toString());
            if (etInput.getText().toString().length() == 0 || tvHomeText.getText().toString().length() == 0 || tvBasetext.getText().toString().length() == 0) {
                return;
            }
            double input;
            try {
                input = Double.parseDouble(etInput.getText().toString());
            } catch (NumberFormatException e) {
                etInput.setText("");
                return;
            }

            double output = input / currencyRate;
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            tvOutputRate.setTextSize(25);
            String outPutText = tvQuantity + " " + homeSymbol + " " + getString(R.string.eq_sgn) + " " + decimalFormat.format(output) + " " + baseSymbol;
            tvOutputRate.setText(outPutText);

        });
        btnFetchRate.setOnClickListener(v -> {
            if (isOnline()) {
                baseSymbol = tvBasetext.getText().toString().trim();
                homeSymbol = tvHomeText.getText().toString().trim();
                fullSymbol = baseSymbol + homeSymbol;
                if (fullSymbol.length() != 0) {
                    
                    fetchCurrData(networkURLRequest.GlobalQuoteRequest(fullSymbol));
                }
            } else {
                showErrorMessage();
            }
        });
    }


    private void showErrorMessage() {
        Toast.makeText(CurrencyExchangeActivity.this, getResources().getText(R.string.no_connectivity_error), Toast.LENGTH_SHORT).show();

    }


    private void fetchCurrData(String urlRequest) {

        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(GET, urlRequest, null, onDataLoaded, onDataError);

        mRequestQueue.add(request);


    }

    private final Response.Listener<JSONObject> onDataLoaded = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {


            try {


                JSONObject series = response.getJSONObject(getString(R.string.time_series_daily));
                JSONObject meta = response.getJSONObject(getString(R.string.meta_data));
                JSONObject lastData = null, secondData = null;
                for (int i = 0; i < series.names().length(); i++) {
                    if (i == 0) lastData = series.getJSONObject(series.names().optString(i));
                    if (i == 1) {
                        secondData = series.getJSONObject(series.names().optString(i));
                        break;
                    }
                }
                if (lastData == null || secondData == null) return;
                progressBar.setVisibility(View.INVISIBLE);
                price = Double.valueOf(lastData.optString(getString(R.string.g_close))).toString();

                progressBar.setVisibility(View.INVISIBLE);
                tvSetRate.setText(price);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private final Response.ErrorListener onDataError = error -> error.printStackTrace();

    private boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveState) {
        saveState.putString(SAVED_SORT, tvOutputRate.getText().toString());
        super.onSaveInstanceState(saveState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        tvOutputRate.setText(saveInstanceState.getString(SAVED_SORT));

    }

}
