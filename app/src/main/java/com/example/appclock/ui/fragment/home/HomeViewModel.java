package com.example.appclock.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ProductModel>> listProductNewHome;
    public List<ProductModel> listProductDefault;

    public HomeViewModel(){
        listProductNewHome = new MutableLiveData<>();
        listProductDefault = new ArrayList<>();
    }

    public void callAPIProductNews(){
        SingletonRetrofit.getInstance().getListProductNews().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                listProductDefault = response.body();
                listProductNewHome.postValue(listProductDefault);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<ProductModel>> getListProductNewHome() {
        return listProductNewHome;
    }
}
