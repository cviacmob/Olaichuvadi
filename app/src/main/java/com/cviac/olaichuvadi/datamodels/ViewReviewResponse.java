package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class ViewReviewResponse implements Serializable {

    private String result;
    private List<ViewReviewInfo> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ViewReviewInfo> getData() {
        return data;
    }

    public void setData(List<ViewReviewInfo> data) {
        this.data = data;
    }
}