package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AddressInfo implements Serializable {

    private String uname;
    private String door_st;
    private String city;
    private String dist;
    private String state;
    private String pin_code;
    private String mobileno;

    public AddressInfo(String uname, String door_st, String city, String dist, String state, String pin_code, String mobileno) {
        this.uname = uname;
        this.door_st = door_st;
        this.city = city;
        this.dist = dist;
        this.state = state;
        this.pin_code = pin_code;
        this.mobileno = mobileno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDoor_st() {
        return door_st;
    }

    public void setDoor_st(String door_st) {
        this.door_st = door_st;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }
}
