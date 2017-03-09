package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;

public class Club_recInfo implements Serializable {

    private String title;
    private int club_img;

    public Club_recInfo(String title, int club_img) {
        this.title = title;
        this.club_img = club_img;
    }

    public Club_recInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClub_img() {
        return club_img;
    }

    public void setClub_img(int club_img) {
        this.club_img = club_img;
    }
}
