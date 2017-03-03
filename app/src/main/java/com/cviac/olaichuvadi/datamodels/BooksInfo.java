package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class BooksInfo implements Serializable {
    private String tit;

    public BooksInfo(String tit) {
        this.tit = tit;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }
}
