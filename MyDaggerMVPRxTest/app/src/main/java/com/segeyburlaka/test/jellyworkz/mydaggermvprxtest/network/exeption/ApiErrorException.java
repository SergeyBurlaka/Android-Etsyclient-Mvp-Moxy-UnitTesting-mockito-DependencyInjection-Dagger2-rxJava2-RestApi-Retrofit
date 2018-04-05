package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption;

import android.support.annotation.Nullable;

public class ApiErrorException extends Exception {

    @Nullable
    private Long mDisplayType;
    @Nullable
    private Long mLevel;
    @Nullable
    private Long mStatusCode;
    @Nullable
    private Long mStatusSubCode;
    @Nullable
    private String mStatusText;

    public ApiErrorException(@Nullable String statusText) {
        super(statusText);
        mStatusText = statusText;
    }

    @Nullable
    public Long getDisplayType() {
        return mDisplayType;
    }

    public void setDisplayType(@Nullable Long displayType) {
        mDisplayType = displayType;
    }

    @Nullable
    public Long getLevel() {
        return mLevel;
    }

    public void setLevel(@Nullable Long level) {
        mLevel = level;
    }

    @Nullable
    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(@Nullable Long statusCode) {
        mStatusCode = statusCode;
    }

    @Nullable
    public Long getStatusSubCode() {
        return mStatusSubCode;
    }

    public void setStatusSubCode(@Nullable Long statusSubCode) {
        mStatusSubCode = statusSubCode;
    }

    @Nullable
    public String getStatusText() {
        return mStatusText;
    }

    public void setStatusText(@Nullable String statusText) {
        mStatusText = statusText;
    }
}
