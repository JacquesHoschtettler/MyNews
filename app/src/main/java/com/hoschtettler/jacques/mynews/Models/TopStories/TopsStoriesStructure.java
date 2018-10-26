package com.hoschtettler.jacques.mynews.Models.TopStories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopStoriesResult;

public class TopsStoriesStructure {

    @SerializedName("results")
    @Expose
    private List<TopStoriesResult> results = null;

    public List<TopStoriesResult> getResults() {
        return results;
    }

    public void setResults(List<TopStoriesResult> results) {
        this.results = results;
    }

}


