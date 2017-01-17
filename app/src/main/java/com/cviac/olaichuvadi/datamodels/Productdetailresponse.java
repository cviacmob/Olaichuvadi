package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class Productdetailresponse implements Serializable {
    private List<ProductDetail> product;

    public List<ProductDetail> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDetail> product) {
        this.product = product;
    }
}
