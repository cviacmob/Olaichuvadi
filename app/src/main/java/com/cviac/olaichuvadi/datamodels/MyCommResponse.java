package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class MyCommResponse implements Serializable {

    private String result;
    private String data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}