package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class ClubResponse extends ReadingClubInfo implements Serializable {

    List<ReadingClubInfo> clubs;

    public List<ReadingClubInfo> getClubs() {
        return clubs;
    }

    public void setClubs(List<ReadingClubInfo> clubs) {
        this.clubs = clubs;
    }
}