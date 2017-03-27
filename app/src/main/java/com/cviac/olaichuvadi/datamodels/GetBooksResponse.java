package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class GetBooksResponse extends GetBooksInfo implements Serializable {

    private String result;
    private List<GetBooksInfo> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<GetBooksInfo> getData() {
        return data;
    }

    public void setData(List<GetBooksInfo> data) {
        this.data = data;
    }
}