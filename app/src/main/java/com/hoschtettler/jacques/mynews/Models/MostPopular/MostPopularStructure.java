package com.hoschtettler.jacques.mynews.Models.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopularStructure {

    @SerializedName("results")
    @Expose
    private List<MostPopularResult> results = null;

    public List<MostPopularResult> getResults() {
        return results;
    }

    public void setResults(List<MostPopularResult> results) {
        this.results = results;
    }

}