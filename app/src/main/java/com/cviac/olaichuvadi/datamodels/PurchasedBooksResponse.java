package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class PurchasedBooksResponse extends PurchasedBooksInfo implements Serializable {

    private List<PurchasedBooksInfo> posts;

    public List<PurchasedBooksInfo> getPosts() {
        return posts;
    }

    public void setPosts(List<PurchasedBooksInfo> posts) {
        this.posts = posts;
    }

}