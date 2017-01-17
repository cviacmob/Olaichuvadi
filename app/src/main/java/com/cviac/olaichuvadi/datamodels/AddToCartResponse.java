package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AddToCartResponse implements Serializable {

    private String success;
    private String error;

    public AddToCartResponse() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
