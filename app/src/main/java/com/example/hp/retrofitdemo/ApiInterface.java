package com.example.hp.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/v2/ticker/?start=0&limit=100&sort=id&structure=array")
    Call<Coin> getCoins();

}
