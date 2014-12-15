
package com.example.listviewdemo.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cursor {

    private String resultCount;
    private List<Page> pages = new ArrayList<Page>();
    private String estimatedResultCount;
    private int currentPageIndex;
    private String moreResultsUrl;
    private String searchResultTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getEstimatedResultCount() {
        return estimatedResultCount;
    }

    public void setEstimatedResultCount(String estimatedResultCount) {
        this.estimatedResultCount = estimatedResultCount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public String getMoreResultsUrl() {
        return moreResultsUrl;
    }

    public void setMoreResultsUrl(String moreResultsUrl) {
        this.moreResultsUrl = moreResultsUrl;
    }

    public String getSearchResultTime() {
        return searchResultTime;
    }

    public void setSearchResultTime(String searchResultTime) {
        this.searchResultTime = searchResultTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
