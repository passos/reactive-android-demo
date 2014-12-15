
package com.example.listviewdemo.schema;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result {

    private String GsearchResultClass;
    private String width;
    private String height;
    private String imageId;
    private String tbWidth;
    private String tbHeight;
    private String unescapedUrl;
    private String url;
    private String visibleUrl;
    private String title;
    private String titleNoFormatting;
    private String originalContextUrl;
    private String content;
    private String contentNoFormatting;
    private String tbUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getGsearchResultClass() {
        return GsearchResultClass;
    }

    public void setGsearchResultClass(String GsearchResultClass) {
        this.GsearchResultClass = GsearchResultClass;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTbWidth() {
        return tbWidth;
    }

    public void setTbWidth(String tbWidth) {
        this.tbWidth = tbWidth;
    }

    public String getTbHeight() {
        return tbHeight;
    }

    public void setTbHeight(String tbHeight) {
        this.tbHeight = tbHeight;
    }

    public String getUnescapedUrl() {
        return unescapedUrl;
    }

    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoFormatting() {
        return contentNoFormatting;
    }

    public void setContentNoFormatting(String contentNoFormatting) {
        this.contentNoFormatting = contentNoFormatting;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
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
