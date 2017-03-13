package com.jpp.memories.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class Memories {
    @SerializedName("result_count")
    @Expose
    private Integer resultCount;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
