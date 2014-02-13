
package com.example.listviewdemo.schema;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GoogleImageSearchResponse {

    private ResponseData responseData;
    private Object responseDetails;
    private int responseStatus;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public Object getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(Object responseDetails) {
        this.responseDetails = responseDetails;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
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
