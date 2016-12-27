package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class OrderInfo implements Serializable{
    private int odrimg;
    private String ordtit;
    private String devsta;
    private String devdat;

    public OrderInfo(int odrimg, String ordtit, String devsta, String devdat) {
        this.odrimg = odrimg;
        this.ordtit = ordtit;
        this.devsta = devsta;
        this.devdat = devdat;
    }

    public int getOdrimg() {
        return odrimg;
    }

    public void setOdrimg(int odrimg) {
        this.odrimg = odrimg;
    }

    public String getOrdtit() {
        return ordtit;
    }

    public void setOrdtit(String ordtit) {
        this.ordtit = ordtit;
    }

    public String getDevsta() {
        return devsta;
    }

    public void setDevsta(String devsta) {
        this.devsta = devsta;
    }

    public String getDevdat() {
        return devdat;
    }

    public void setDevdat(String devdat) {
        this.devdat = devdat;
    }
}
