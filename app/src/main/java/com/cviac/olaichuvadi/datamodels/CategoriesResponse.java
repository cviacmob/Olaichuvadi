package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class CategoriesResponse implements Serializable {
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
