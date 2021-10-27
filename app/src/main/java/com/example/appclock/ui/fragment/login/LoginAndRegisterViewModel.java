package com.example.appclock.ui.fragment.login;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAndRegisterViewModel extends ViewModel {
    private MutableLiveData<List<AccountModel>> listAccount;
    private List<AccountModel> list;

    public LoginAndRegisterViewModel(){
        listAccount = new MutableLiveData<>();
        list = new ArrayList<>();
    }

    public MutableLiveData<List<AccountModel>> getListAccount(){
        return listAccount;
    }

    public void getApiAccount(){
        SingletonRetrofit.getInstance().getListAccount().enqueue(new Callback<List<AccountModel>>() {
            @Override
            public void onResponse(Call<List<AccountModel>> call, Response<List<AccountModel>> response) {
                list = response.body();
                listAccount.postValue(list);
            }

            @Override
            public void onFailure(Call<List<AccountModel>> call, Throwable t) {
                listAccount.postValue(null);
            }
        });
    }

    public void postApiAccount(AccountModel accountModel){
        list.add(accountModel);
        listAccount.postValue(list);
        SingletonRetrofit.getInstance().postAccount(accountModel).enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
            }
            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
            }
        });
    }
}
