package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class ViewAddedBooksInfo implements Serializable {

    private String customer_id;
    private String isbn;
    private String date_added;
    private String sell_price;
    private String share_price;
    private String lend_price;
    private String min_bid_price;
    private String max_bid_price;
    private String availability;
    private String status;
    private String title;
    private String author;
    private String cover_type;
    private String no_of_pages;
    private String publisher;
    private String image;
    private String description;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getShare_price() {
        return share_price;
    }

    public void setShare_price(String share_price) {
        this.share_price = share_price;
    }

    public String getLend_price() {
        return lend_price;
    }

    public void setLend_price(String lend_price) {
        this.lend_price = lend_price;
    }

    public String getMin_bid_price() {
        return min_bid_price;
    }

    public void setMin_bid_price(String min_bid_price) {
        this.min_bid_price = min_bid_price;
    }

    public String getMax_bid_price() {
        return max_bid_price;
    }

    public void setMax_bid_price(String max_bid_price) {
        this.max_bid_price = max_bid_price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover_type() {
        return cover_type;
    }

    public void setCover_type(String cover_type) {
        this.cover_type = cover_type;
    }

    public String getNo_of_pages() {
        return no_of_pages;
    }

    public void setNo_of_pages(String no_of_pages) {
        this.no_of_pages = no_of_pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}