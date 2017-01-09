package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class BooksInfo implements Serializable {

    private int books;

    private String book_tit;

    private String book_author;

    private int edt_img;

    public BooksInfo(int books, String book_tit, String book_author, int edt_img) {
        this.books = books;
        this.book_tit = book_tit;
        this.book_author = book_author;
        this.edt_img = edt_img;
    }

    public int getEdt_img() {
        return edt_img;
    }

    public void setEdt_img(int edt_img) {
        this.edt_img = edt_img;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public String getBook_tit() {
        return book_tit;
    }

    public void setBook_tit(String book_tit) {
        this.book_tit = book_tit;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
}
