package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class CartInfo implements Serializable {

    private int prdimg;
    private String title;
    private String desc;
    private String price;

    public CartInfo(int prdimg, String title, String desc, String price) {
        this.prdimg = prdimg;
        this.title = title;
        this.desc = desc;
        this.price = price;
    }

    public int getPrdimg() {
        return prdimg;
    }

    public void setPrdimg(int prdimg) {
        this.prdimg = prdimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}