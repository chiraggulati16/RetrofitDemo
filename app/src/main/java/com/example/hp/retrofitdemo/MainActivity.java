package com.example.hp.retrofitdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CoinAdapter adapter;
    private List<ListItem> items;
    private List<ListItem> item2;
    private ApiInterface apiInterface;
    private Coin coin;
    Boolean isScrolling = false;
    int index;
    int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        items = new ArrayList<>();
        item2=new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    getData();
                }
            }
        });

    }
    public void getData() {
            Call<Coin> call = apiInterface.getCoins();
            call.enqueue(new Callback<Coin>() {
                @Override
                public void onResponse(Call<Coin> call, Response<Coin> response) {
                    Coin coin = response.body();
                    index=coin.getMetadata().getNumCryptocurrencies();
                    for (int i = 0; i < coin.getData().size(); i++) {
                        String id = String.valueOf(coin.getData().get(i).getId());
                        String name = coin.getData().get(i).getName();
                        String symbol = coin.getData().get(i).getSymbol();
                        DecimalFormat decimal = new DecimalFormat("0.00");
                        Double pricevalue = coin.getData().get(i).getQuotes().getUSD().getPrice();
                        String price = decimal.format(pricevalue);
                        Double marketvalue = coin.getData().get(i).getQuotes().getUSD().getMarketCap();
                        String market = decimal.format(marketvalue);
                        Double volumeValue = coin.getData().get(i).getQuotes().getUSD().getVolume24h();
                        String volume = decimal.format(volumeValue);
                        Double oneHourValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange1h();
                        String oneHour = decimal.format(oneHourValue).concat("%");
                        Double twentyHourValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange24h();
                        String twentyHour = decimal.format(twentyHourValue).concat("%");
                        Double sevenDayValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange7d();
                        String sevenDay = decimal.format(sevenDayValue).concat("%");
                        if (oneHourValue < 0) {

                        } else {
                            oneHour = "+" + oneHour;

                        }
                        if (twentyHourValue < 0) {
                        } else {
                            twentyHour = "+" + twentyHour;

                        }
                        if (sevenDayValue < 0) {
                        } else {
                            sevenDay = "+" + sevenDay;

                        }
                        ListItem listItem1 = new ListItem(id, name, symbol, price, market, volume, oneHour, twentyHour, sevenDay);
                        items.add(listItem1);
                      //  adapter.notifyDataSetChanged();
                    }
                    adapter = new CoinAdapter(items, MainActivity.this);
                    recyclerView.setAdapter(adapter);


                }

                @Override
                public void onFailure(Call<Coin> call, Throwable t) {
                    Log.e("tag=", "" + t.getMessage());

                }
            });
        //}

     /*   else {
            Toast.makeText(this,"Data max is "+coin.getMetadata().getNumCryptocurrencies(),Toast.LENGTH_LONG).show();
        }*/
    }


    }