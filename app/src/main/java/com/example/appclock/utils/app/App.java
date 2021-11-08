package com.example.appclock.utils.app;

import android.app.Application;

public class App extends Application {
    private static App instance;
    private Boolean checkLoginAccountorGmail = false;
    private String checkIdLogin ;


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
}
