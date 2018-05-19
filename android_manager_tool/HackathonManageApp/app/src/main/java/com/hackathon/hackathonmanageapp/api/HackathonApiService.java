package com.hackathon.hackathonmanageapp.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface HackathonApiService {
    @GET
    public Call<String> getBeeGoods(@HeaderMap HashMap<String, String> header, @Url String url);

    @GET("product/list")
    public Call<String> getGoodsList();

    @PUT("product/update/price")
    public Call<String> updatePrice(@Body String body);

    @GET("data/list")
    public Call<String> getRawDatas();
}
