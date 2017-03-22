package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AuthorInfo implements Serializable {

    private String author_id;
    private String author_name;
    private String author_image;
    private String author_occupation;
    private String author_nationality;
    private String author_education;
    private String author_awards;
    private String author_references;
    private String author_external_links;
    private String likes;
    private String votes;

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_image() {
        return author_image;
    }

    public void setAuthor_image(String author_image) {
        this.author_image = author_image;
    }

    public String getAuthor_occupation() {
        return author_occupation;
    }

    public void setAuthor_occupation(String author_occupation) {
        this.author_occupation = author_occupation;
    }

    public String getAuthor_nationality() {
        return author_nationality;
    }

    public void setAuthor_nationality(String author_nationality) {
        this.author_nationality = author_nationality;
    }

    public String getAuthor_education() {
        return author_education;
    }

    public void setAuthor_education(String author_education) {
        this.author_education = author_education;
    }

    public String getAuthor_awards() {
        return author_awards;
    }

    public void setAuthor_awards(String author_awards) {
        this.author_awards = author_awards;
    }

    public String getAuthor_references() {
        return author_references;
    }

    public void setAuthor_references(String author_references) {
        this.author_references = author_references;
    }

    public String getAuthor_external_links() {
        return author_external_links;
    }

    public void setAuthor_external_links(String author_external_links) {
        this.author_external_links = author_external_links;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}