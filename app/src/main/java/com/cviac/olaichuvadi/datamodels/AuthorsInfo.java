package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AuthorsInfo implements Serializable {
    private String author_tit;

    public AuthorsInfo(String author_tit) {
        this.author_tit = author_tit;
    }

    public String getAuthor_tit() {
        return author_tit;
    }

    public void setAuthor_tit(String author_tit) {
        this.author_tit = author_tit;
    }

}