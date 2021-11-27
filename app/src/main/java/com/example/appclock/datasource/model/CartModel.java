package com.example.appclock.datasource.model;

public class CartModel {
        private String id;
        private String phoneUser;
        private String addressUser;
        private String idUser;

        public CartModel(String id, String phoneUser, String addressUser, String idUser) {
            this.id = id;
            this.phoneUser = phoneUser;
            this.addressUser = addressUser;
            this.idUser = idUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoneUser() {
            return phoneUser;
        }

        public void setPhoneUser(String phoneUser) {
            this.phoneUser = phoneUser;
        }

        public String getAddressUser() {
            return addressUser;
        }

        public void setAddressUser(String addressUser) {
            this.addressUser = addressUser;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }
}
