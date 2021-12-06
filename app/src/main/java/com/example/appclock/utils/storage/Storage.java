package com.example.appclock.utils.storage;

import com.example.appclock.datasource.model.CartDetailModel;
import com.example.appclock.datasource.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage storage;
    private List<CartDetailModel> listCartDetail = new ArrayList<>();
    private int checkOrder = 0;
    private String textSearch;
    private List<ProductModel> listRolexDefault , listHublotDefault , listPiagetDefault ;

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

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public List<ProductModel> getListRolexDefault() {
        return listRolexDefault;
    }

    public void setListRolexDefault(List<ProductModel> listRolexDefault) {
        this.listRolexDefault = listRolexDefault;
    }

    public List<ProductModel> getListHublotDefault() {
        return listHublotDefault;
    }

    public void setListHublotDefault(List<ProductModel> listHublotDefault) {
        this.listHublotDefault = listHublotDefault;
    }

    public List<ProductModel> getListPiagetDefault() {
        return listPiagetDefault;
    }

    public void setListPiagetDefault(List<ProductModel> listPiagetDefault) {
        this.listPiagetDefault = listPiagetDefault;
    }
}
