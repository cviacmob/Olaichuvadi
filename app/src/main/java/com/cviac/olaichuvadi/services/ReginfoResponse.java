package com.cviac.olaichuvadi.services;

public class ReginfoResponse {

    private int code;
    private String desc;
    private int customer_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    //    private CustomerInfo customer;
//
    public ReginfoResponse() {
    }

//    public CustomerInfo getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(CustomerInfo customer) {
//        this.customer = customer;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
