package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class CartTotalInfo implements Serializable {
    private String title;

    private String text;

    public CartTotalInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
