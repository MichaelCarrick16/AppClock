package com.example.appclock.datasource.model;

public class ProductModel {
    private int idProduct;
    private String nameProduct;
    private int priceProduct;
    private  String imageProduct;
    private String descriptionProduct;
    private int idTrademark;

    public ProductModel(int idProduct, String nameProduct, int priceProduct, String imageProduct, String descriptionProduct, int idTrademark) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.descriptionProduct = descriptionProduct;
        this.idTrademark = idTrademark;
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

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getIdTrademark() {
        return idTrademark;
    }

    public void setIdTrademark(int idTrademark) {
        this.idTrademark = idTrademark;
    }
}
