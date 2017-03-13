package com.jpp.memories.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class ReferralDestination {
    @SerializedName("site_name")
    @Expose
    private String siteName;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
