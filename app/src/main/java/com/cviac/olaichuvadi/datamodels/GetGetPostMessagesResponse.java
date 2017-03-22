package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class GetGetPostMessagesResponse extends GetPostInfo implements Serializable {
    List<GetPostInfo> posts;

    public List<GetPostInfo> getPosts() {
        return posts;
    }

    public void setPosts(List<GetPostInfo> posts) {
        this.posts = posts;
    }
}