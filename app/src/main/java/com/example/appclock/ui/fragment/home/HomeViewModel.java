package com.example.appclock.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.datasource.model.ProductModel;
import com.example.appclock.datasource.model.TrademarkModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ProductModel>> listProductNewHome;
    public List<ProductModel> listProductDefault;
    private List<TrademarkModel> listTradeMark;
    private List<ProductModel> listRolex;
    private List<ProductModel> listHublot;
    private List<ProductModel> listPiaget;
    public List<CartDetailModel> listCartDetail;
    private MutableLiveData<List<CartDetailModel>> listCartDetailUpdate;

    public HomeViewModel(){
        listProductNewHome = new MutableLiveData<>();
        listProductDefault = new ArrayList<>();
        listRolex = new ArrayList<>();
        listHublot = new ArrayList<>();
        listPiaget = new ArrayList<>();
        listCartDetail = new ArrayList<>();
        listCartDetailUpdate = new MutableLiveData<>();
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

    public void callAPITrademark(){
        SingletonRetrofit.getInstance().getListTrademark().enqueue(new Callback<List<TrademarkModel>>() {
            @Override
            public void onResponse(Call<List<TrademarkModel>> call, Response<List<TrademarkModel>> response) {
                    listTradeMark = response.body();
            }

            @Override
            public void onFailure(Call<List<TrademarkModel>> call, Throwable t) {

            }
        });
    }

    public void callAPIListProductByTrademark(){
        SingletonRetrofit.getInstance().getListProduct(1).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                listRolex = response.body();
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        SingletonRetrofit.getInstance().getListProduct(2).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                listHublot = response.body();
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        SingletonRetrofit.getInstance().getListProduct(3).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                listPiaget = response.body();
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<ProductModel>> getListProductNewHome() {
        return listProductNewHome;
    }

    public List<TrademarkModel> getListTradeMark() {
        return listTradeMark;
    }

    public List<ProductModel> getListRolex() {
        return listRolex;
    }

    public List<ProductModel> getListHublot() {
        return listHublot;
    }

    public List<ProductModel> getListPiaget() {
        return listPiaget;
    }

    public MutableLiveData<List<CartDetailModel>> getListCartDetailUpdate() {
        return listCartDetailUpdate;
    }


}
