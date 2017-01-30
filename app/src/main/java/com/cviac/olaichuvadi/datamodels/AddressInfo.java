package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class AddressInfo implements Serializable {

    private String fname;
    private String lname;
    private String addr;
    private String city;
    private String dist;
    private String state;
    private String pin_code;
    private String mobileno;

    public AddressInfo(String fname, String lname, String addr, String city, String dist, String state, String pin_code, String mobileno) {
        this.fname = fname;
        this.lname = lname;
        this.addr = addr;
        this.city = city;
        this.dist = dist;
        this.state = state;
        this.pin_code = pin_code;
        this.mobileno = mobileno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}