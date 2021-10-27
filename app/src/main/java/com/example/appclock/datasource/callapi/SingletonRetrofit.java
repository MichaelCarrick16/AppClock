package com.example.appclock.datasource.callapi;

import com.example.appclock.utils.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofit {
    private static Retrofit retrofit = null;

    public static ApiService getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

}
