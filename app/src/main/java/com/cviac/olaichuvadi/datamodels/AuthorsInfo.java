package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AuthorsInfo implements Serializable {
    private String author_tit;
    private int author_img;

    public AuthorsInfo(String author_tit, int author_img) {
        this.author_tit = author_tit;
        this.author_img = author_img;
    }

    public String getAuthor_tit() {
        return author_tit;
    }

    public void setAuthor_tit(String author_tit) {
        this.author_tit = author_tit;
    }

    public int getAuthor_img() {
        return author_img;
    }

    public void setAuthor_img(int author_img) {
        this.author_img = author_img;
    }
}
