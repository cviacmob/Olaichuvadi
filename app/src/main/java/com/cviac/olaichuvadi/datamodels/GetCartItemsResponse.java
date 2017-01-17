package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class GetCartItemsResponse implements Serializable {

    private List<ProductCartInfo> products;

    private List<CartTotalInfo> totals;

    public GetCartItemsResponse() {
    }

    public List<ProductCartInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCartInfo> products) {
        this.products = products;
    }

    public List<CartTotalInfo> getTotals() {
        return totals;
    }

    public void setTotals(List<CartTotalInfo> totals) {
        this.totals = totals;
    }
}
