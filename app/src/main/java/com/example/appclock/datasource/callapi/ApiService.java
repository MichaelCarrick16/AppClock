package com.example.appclock.datasource.callapi;

import com.example.appclock.datasource.model.AccountModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/login")
    Call<List<AccountModel>> getListAccount();

    @POST("/login")
    Call<AccountModel> postAccount(@Body AccountModel accountModel);
}
