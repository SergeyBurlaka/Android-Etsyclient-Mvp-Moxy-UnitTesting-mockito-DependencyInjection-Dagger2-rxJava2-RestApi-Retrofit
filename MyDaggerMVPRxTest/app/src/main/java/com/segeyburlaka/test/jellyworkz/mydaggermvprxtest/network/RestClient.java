package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.JacksonUtil.createObjectMapper;


public class RestClient {
    private static ObjectMapper sMapper = createObjectMapper();

    private final Retrofit RETROFIT = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create(sMapper))
            .baseUrl(NetworkContract.Base.API_ENDPOINT)
            .client(createGoogleHttpClient())
            .build();

    private OkHttpClient createGoogleHttpClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(NetworkContract.Base.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NetworkContract.Base.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetworkContract.Base.TIMEOUT, TimeUnit.SECONDS);
        return okHttpBuilder.build();
    }

    public Retrofit getRestClient() {
        return RETROFIT;
    }
}
