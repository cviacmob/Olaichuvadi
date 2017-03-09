package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class SharedInfo implements Serializable{

    private String book_tit;
    private int book_img;
    private String uname;
    private String price;

    public SharedInfo(String book_tit, int book_img, String uname, String price) {
        this.book_tit = book_tit;
        this.book_img = book_img;
        this.uname = uname;
        this.price = price;
    }

    public SharedInfo(String book_tit) {
        this.book_tit = book_tit;
    }

    public String getBook_tit() {
        return book_tit;
    }

    public void setBook_tit(String book_tit) {
        this.book_tit = book_tit;
    }

    public int getBook_img() {
        return book_img;
    }

    public void setBook_img(int book_img) {
        this.book_img = book_img;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
