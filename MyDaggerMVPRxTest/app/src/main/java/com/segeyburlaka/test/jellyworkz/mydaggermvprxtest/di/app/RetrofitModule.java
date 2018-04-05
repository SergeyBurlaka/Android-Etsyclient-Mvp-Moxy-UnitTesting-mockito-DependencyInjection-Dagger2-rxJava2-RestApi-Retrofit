package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public RestClient provideDaoSession() {
        return new RestClient();
    }
}
