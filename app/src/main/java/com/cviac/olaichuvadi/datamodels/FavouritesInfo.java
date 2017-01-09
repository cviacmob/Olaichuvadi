package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class FavouritesInfo implements Serializable {

    private int fav_img;

    private String fav_tit;

    private String fav_auth;

    public FavouritesInfo(int fav_img, String fav_tit, String fav_auth) {
        this.fav_img = fav_img;
        this.fav_tit = fav_tit;
        this.fav_auth = fav_auth;
    }

    public int getFav_img() {
        return fav_img;
    }

    public void setFav_img(int fav_img) {
        this.fav_img = fav_img;
    }

    public String getFav_tit() {
        return fav_tit;
    }

    public void setFav_tit(String fav_tit) {
        this.fav_tit = fav_tit;
    }

    public String getFav_auth() {
        return fav_auth;
    }

    public void setFav_auth(String fav_auth) {
        this.fav_auth = fav_auth;
    }
}