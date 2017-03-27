package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class ViewAddedBooksResponse extends ViewAddedBooksInfo implements Serializable {
    private String result;
    private List<ViewAddedBooksInfo> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ViewAddedBooksInfo> getData() {
        return data;
    }

    public void setData(List<ViewAddedBooksInfo> data) {
        this.data = data;
    }
}