package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.base;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "effective_limit",
        "effective_offset",
        "next_offset",
        "effective_page",
        "next_page"
})
public class Pagination {

    @JsonProperty("effective_limit")
    private Integer mEffectiveLimit;
    @JsonProperty("effective_offset")
    private Integer mEffectiveOffset;
    @JsonProperty("next_offset")
    private Integer mNextOffset;
    @JsonProperty("effective_page")
    private Integer mEffectivePage;
    @JsonProperty("next_page")
    private Integer mNextPage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("effective_limit")
    public Integer getEffectiveLimit() {
        return mEffectiveLimit;
    }

    @JsonProperty("effective_limit")
    public void setEffectiveLimit(Integer effectiveLimit) {
        this.mEffectiveLimit = effectiveLimit;
    }

    @JsonProperty("effective_offset")
    public Integer getEffectiveOffset() {
        return mEffectiveOffset;
    }

    @JsonProperty("effective_offset")
    public void setEffectiveOffset(Integer effectiveOffset) {
        this.mEffectiveOffset = effectiveOffset;
    }

    @JsonProperty("next_offset")
    public Integer getNextOffset() {
        return mNextOffset;
    }

    @JsonProperty("next_offset")
    public void setNextOffset(Integer nextOffset) {
        this.mNextOffset = nextOffset;
    }

    @JsonProperty("effective_page")
    public Integer getEffectivePage() {
        return mEffectivePage;
    }

    @JsonProperty("effective_page")
    public void setEffectivePage(Integer effectivePage) {
        this.mEffectivePage = effectivePage;
    }

    @JsonProperty("next_page")
    public Integer getNextPage() {
        return mNextPage;
    }

    @JsonProperty("next_page")
    public void setNextPage(Integer nextPage) {
        this.mNextPage = nextPage;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
