package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class ProductDetail extends Product implements Serializable {

    public ProductDetail(String product_id, String name, String description, String thumb, String rating, String href, String price, String special) {
        super(product_id, name, description, thumb, rating, href, price, special);
    }

}
