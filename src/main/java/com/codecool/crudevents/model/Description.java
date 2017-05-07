package com.codecool.crudevents.model;

/**
 * Created by mercutio on 07.05.17.
 */
public class Description {
    private String basicInfo;
    private String additionalInfo;


    public Description(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Description(String basicInfo, String additionalInfo) {
        this.basicInfo = basicInfo;
        this.additionalInfo = additionalInfo;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
