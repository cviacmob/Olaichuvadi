package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class PurchasedInfo implements Serializable {

    private int book_img;

    private String tit;

    private String auth;

    public PurchasedInfo(int book_img, String tit, String auth) {
        this.book_img = book_img;
        this.tit = tit;
        this.auth = auth;
    }

    public int getBook_img() {
        return book_img;
    }

    public void setBook_img(int book_img) {
        this.book_img = book_img;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
