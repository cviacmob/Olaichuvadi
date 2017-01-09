package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class ReviewedInfo implements Serializable {

    private int rev_books;

    private String rev_tit;

    private String rev_auth;

    public ReviewedInfo(int rev_books, String rev_tit, String rev_auth) {
        this.rev_books = rev_books;
        this.rev_tit = rev_tit;
        this.rev_auth = rev_auth;
    }

    public int getRev_books() {
        return rev_books;
    }

    public void setRev_books(int rev_books) {
        this.rev_books = rev_books;
    }

    public String getRev_tit() {
        return rev_tit;
    }

    public void setRev_tit(String rev_tit) {
        this.rev_tit = rev_tit;
    }

    public String getRev_auth() {
        return rev_auth;
    }

    public void setRev_auth(String rev_auth) {
        this.rev_auth = rev_auth;
    }
}
