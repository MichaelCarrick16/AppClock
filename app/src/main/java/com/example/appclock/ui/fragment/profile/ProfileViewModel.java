package com.example.appclock.ui.fragment.profile;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appclock.datasource.callapi.SingletonRetrofit;
import com.example.appclock.datasource.model.AccountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<AccountModel> accountModel;

    public ProfileViewModel() {
        accountModel = new MutableLiveData<>();
    }

    public MutableLiveData<AccountModel> getAccount(){
        return accountModel;
    }

    public void getApiAccount(String username){
        SingletonRetrofit.getInstance().getAccount(username).enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                accountModel.postValue(response.body());
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {

            }
        });
    }

    public void updateApiAccount(String username , AccountModel accountModel , Context context){
        final Boolean[] check = {true};
        SingletonRetrofit.getInstance().putAccount(username,accountModel).enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                getAccount().postValue(response.body());
                check[0] = true;
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                check[0] = false;
            }
        });
        if(check[0]){
            Toast.makeText(context,"Update Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
        }
    }


}
