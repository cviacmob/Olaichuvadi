package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class AuthorsResponse extends AuthorInfo implements Serializable {

    List<AuthorInfo> authors;

    public List<AuthorInfo> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorInfo> authors) {
        this.authors = authors;
    }
}