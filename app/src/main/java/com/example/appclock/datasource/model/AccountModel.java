package com.example.appclock.datasource.model;

public class AccountModel {
    private String username;
    private String password;
    private String address;
    private String phonenumber;
    private String name;

    public AccountModel(String username, String passWord, String addRess, String phoneNumber , String name) {
        this.username = username;
        this.password = passWord;
        this.address = addRess;
        this.phonenumber = phoneNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
