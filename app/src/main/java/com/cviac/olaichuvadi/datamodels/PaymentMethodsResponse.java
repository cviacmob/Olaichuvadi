package com.cviac.olaichuvadi.datamodels;

import java.util.List;

public class PaymentMethodsResponse {

    private List<PaymentMethodsInfo> payment_methods;

    public List<PaymentMethodsInfo> getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(List<PaymentMethodsInfo> payment_methods) {
        this.payment_methods = payment_methods;
    }
}