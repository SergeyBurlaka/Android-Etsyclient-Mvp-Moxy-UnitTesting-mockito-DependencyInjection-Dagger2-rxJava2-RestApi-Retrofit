package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


class ServiceError implements Serializable {
    @JsonProperty("key")
    private Integer mKey;

    @JsonProperty("value")
    private String mMessage;

    public ServiceError() {
//        need for deserialize
    }

    public Integer getKey() {
        return mKey;
    }

    public String getMessage() {
        return mMessage;
    }
}
