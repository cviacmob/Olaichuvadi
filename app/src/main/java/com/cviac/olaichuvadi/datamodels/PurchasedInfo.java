package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class PurchasedInfo implements Serializable {

    private String book_title;

    public PurchasedInfo(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
}
