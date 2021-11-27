package com.example.appclock.utils.app;

import android.app.Application;

import com.example.appclock.datasource.model.AccountModel;
import com.example.appclock.datasource.model.CartModel;

public class App extends Application {
    private static App instance;
    private Boolean checkLoginAccountorGmail = false;
    // Kiểm tra nếu  click Order mà chưa có product  thì logout phải remove đi luôn cái Cart mới tạo lúc login . còn nếu có product click order thì k xóa
    private Boolean checkButtonOrder = true;
    private String checkIdLogin;
    private AccountModel accountLogin;
    private CartModel cartLogin;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        if(instance == null){
            instance = new App();
        }
        return instance;
    }

    public Boolean getCheckLoginAccountorGmail() {
        return checkLoginAccountorGmail;
    }

    public void setCheckLoginAccountorGmail(Boolean checkLoginAccountorGmail) {
        this.checkLoginAccountorGmail = checkLoginAccountorGmail;
    }

    public String getCheckIdLogin() {
        return checkIdLogin;
    }

    public void setCheckIdLogin(String checkIdLogin) {
        this.checkIdLogin = checkIdLogin;
    }

    public AccountModel getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(AccountModel accountLogin) {
        this.accountLogin = accountLogin;
    }

    public CartModel getCartLogin() {
        return cartLogin;
    }

    public void setCartLogin(CartModel cartLogin) {
        this.cartLogin = cartLogin;
    }

    public Boolean getCheckButtonOrder() {
        return checkButtonOrder;
    }

    public void setCheckButtonOrder(Boolean checkButtonOrder) {
        this.checkButtonOrder = checkButtonOrder;
    }
}
