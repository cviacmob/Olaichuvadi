package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class CategoryProductsResponse implements Serializable {

    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
