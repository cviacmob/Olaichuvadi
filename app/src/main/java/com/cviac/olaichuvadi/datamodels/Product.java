package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class Product implements Serializable {

    private String product_id;
    private String name;
    private String description;
    private String thumb;
    private String rating;
    private String href;
    private String price;
    private String special;

    public Product(String product_id, String name, String description, String thumb, String rating, String href, String price, String special) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.thumb = thumb;
        this.rating = rating;
        this.href = href;
        this.price = price;
        this.special = special;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
