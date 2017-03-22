package com.cviac.olaichuvadi.datamodels;

import java.io.Serializable;
import java.util.List;

public class PublishersResponse extends PublisherInfo implements Serializable {

    List<PublisherInfo> publishers;

    public List<PublisherInfo> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<PublisherInfo> publishers) {
        this.publishers = publishers;
    }
}