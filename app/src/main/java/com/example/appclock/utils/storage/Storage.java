package com.example.appclock.utils.storage;

import com.example.appclock.datasource.model.CartDetailModel;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage storage;
    private List<CartDetailModel> listCartDetail = new ArrayList<>();
    private int checkOrder = 0;

    private Storage(){

    }

    public static Storage getStorage() {
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }


    public List<CartDetailModel> getListCartDetail() {
        return listCartDetail;
    }

    public void setListCartDetail(List<CartDetailModel> listCartDetail) {
        this.listCartDetail = listCartDetail;
    }

    public int getCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(int checkOrder) {
        this.checkOrder = checkOrder;
    }
}
