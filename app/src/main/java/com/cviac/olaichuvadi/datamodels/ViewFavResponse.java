package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class ViewFavResponse extends ViewFavInfo implements Serializable {
    private String result;
    private List<ViewFavInfo> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ViewFavInfo> getData() {
        return data;
    }

    public void setData(List<ViewFavInfo> data) {
        this.data = data;
    }
}