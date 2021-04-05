package com.wlhse.dto;

import org.springframework.stereotype.Component;

@Component
public class Standard {
    private Integer standardID;
    private String sandardName;
    private String description;
    private String docURL;
    private String docName;

    public Standard() {
    }

    public Integer getStandardID() {
        return standardID;
    }

    public String getSandardName() {
        return sandardName;
    }

    public String getDescription() {
        return description;
    }

    public String getDocURL() {
        return docURL;
    }

    public String getDocName() {
        return docName;
    }

    public void setStandardID(Integer standardID) {
        this.standardID = standardID;
    }

    public void setSandardName(String sandardName) {
        this.sandardName = sandardName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
