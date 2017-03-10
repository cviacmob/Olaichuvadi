package com.cviac.olaichuvadi.datamodels;

import java.util.List;

public class ShippingMethodsResponse {

    private List<ShippingMethodsInfo> shipping_methods;

    public List<ShippingMethodsInfo> getShipping_methods() {
        return shipping_methods;
    }

    public void setShipping_methods(List<ShippingMethodsInfo> shipping_methods) {
        this.shipping_methods = shipping_methods;
    }
}