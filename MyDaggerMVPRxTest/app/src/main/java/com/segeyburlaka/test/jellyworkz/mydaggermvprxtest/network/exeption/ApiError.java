package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ApiError extends Exception {

    private static final String UNKNOWN_ERROR = "Unknown server error";

    @JsonProperty("errors")
    private List<ServiceError> mErrors;

    public List<ServiceError> getErrors() {
        return mErrors;
    }

    @Override
    public String getMessage() {
        if (mErrors != null && !mErrors.isEmpty()) {
            String message = mErrors.get(0).getMessage();
            if (!TextUtils.isEmpty(message)) {
                return message;
            }
        }
        return UNKNOWN_ERROR;
    }
}
