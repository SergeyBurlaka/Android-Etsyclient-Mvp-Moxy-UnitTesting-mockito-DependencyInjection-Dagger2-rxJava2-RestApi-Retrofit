package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.base;


import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public abstract class BaseJsonDataBean<T> extends BaseJsonBean {
    public static final String DATA = "results";
    @Nullable
    @JsonProperty(DATA)
    private List<T> mResults;

    @Nullable
    public List<T> getResults() {
        return mResults;
    }

    public void setResults(@Nullable List<T> results) {
        mResults = results;
    }
}
