package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.base;


import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count",
        "results",
        "params",
        "type",
        "pagination"
})
public abstract class BaseJsonBean {

    @Nullable
    @JsonProperty("count")
    private Integer mCount;
    @JsonProperty("params")
    private Params mParams;
    @Nullable
    @JsonProperty("type")
    private String mType;
    @Nullable
    @JsonProperty("pagination")
    private Pagination mStatusCode;

    public Params getParams() {
        return mParams;
    }

    public void setParams(Params params) {
        mParams = params;
    }

    @Nullable
    public Integer getCount() {
        return mCount;
    }

    public void setCount(@Nullable Integer count) {
        mCount = count;
    }

    @Nullable
    public String getType() {
        return mType;
    }

    public void setType(@Nullable String type) {
        mType = type;
    }

    @Nullable
    public Pagination getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(@Nullable Pagination statusCode) {
        mStatusCode = statusCode;
    }

}
