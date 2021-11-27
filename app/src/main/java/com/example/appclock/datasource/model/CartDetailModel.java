package com.example.appclock.datasource.model;

public class CartDetailModel {
    private String idCartDetail;
    private String idCart;
    private int idProduct;
    private String nameProduct;
    private String pictureProduct;
    private int amountProduct;
    private int priceProduct;

    public CartDetailModel(String idCartDetail, String idCart, int idProduct, String nameProduct, String pictureProduct, int amountProduct, int priceProduct) {
        this.idCartDetail = idCartDetail;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.pictureProduct = pictureProduct;
        this.amountProduct = amountProduct;
        this.priceProduct = priceProduct;
    }

    public String getIdCartDetail() {
        return idCartDetail;
    }

    public void setIdCartDetail(String idCartDetail) {
        this.idCartDetail = idCartDetail;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPictureProduct() {
        return pictureProduct;
    }

    public void setPictureProduct(String pictureProduct) {
        this.pictureProduct = pictureProduct;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }
}
