package com.example.appclock.datasource.callapi;

import com.example.appclock.datasource.model.AccountModel;
import com.example.appclock.datasource.model.ProductModel;
import com.example.appclock.datasource.model.TrademarkModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/login")
    Call<List<AccountModel>> getListAccount();

    @GET("/login/{username}")
    Call<AccountModel> getAccount(@Path("username") String username);

    @POST("/login")
    Call<AccountModel> postAccount(@Body AccountModel accountModel);

    @PUT("/login/{username}")
    Call<AccountModel> putAccount(@Path("username") String username ,@Body AccountModel accountModel);

    @GET("/trademark")
    Call<List<TrademarkModel>> getListTrademark();

    @GET("/product/{idTrademark}")
    Call<List<ProductModel>> getListProduct(@Path("idTrademark") int idTrademark);

    @GET("/product/news")
    Call<List<ProductModel>> getListProductNews();
}
