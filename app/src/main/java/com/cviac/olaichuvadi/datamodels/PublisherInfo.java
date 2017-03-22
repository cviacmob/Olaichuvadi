package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class PublisherInfo implements Serializable {

    private String publisher_id;
    private String publisher_name;
    private String publisher_image;
    private String publisher_address;
    private String publisher_description;
    private String likes;
    private String total_votes;

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getPublisher_image() {
        return publisher_image;
    }

    public void setPublisher_image(String publisher_image) {
        this.publisher_image = publisher_image;
    }

    public String getPublisher_address() {
        return publisher_address;
    }

    public void setPublisher_address(String publisher_address) {
        this.publisher_address = publisher_address;
    }

    public String getPublisher_description() {
        return publisher_description;
    }

    public void setPublisher_description(String publisher_description) {
        this.publisher_description = publisher_description;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(String total_votes) {
        this.total_votes = total_votes;
    }
}