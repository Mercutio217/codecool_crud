package com.codecool.crudevents.model;

/**
 * Created by mercutio on 07.05.17.
 */
public class Description {
    private String info;
    private String additionalInfo;


    public Description(String info) {
        this.info = info;
    }

    public Description(String info, String additionalInfo) {
        this.info = info;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return String.format("%s \n %s", this.info, this.additionalInfo);
    }

    public String getBasicInfo() {
        return info;
    }

    public void setBasicInfo(String info) {
        this.info = info;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
