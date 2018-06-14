package com.purdynet.faviconapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "favicons")
public class FaviconRecord {
    @Id
    private String baseUrl;
    private String faviconUrl;
    private Date dateCreated;

    public FaviconRecord() {}

    public FaviconRecord(String baseUrl, String faviconUrl) {
        this.baseUrl = baseUrl;
        this.faviconUrl = faviconUrl;
        this.dateCreated = new Date();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
