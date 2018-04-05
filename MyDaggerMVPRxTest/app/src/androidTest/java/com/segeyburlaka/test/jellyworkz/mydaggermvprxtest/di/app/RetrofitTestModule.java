package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitTestModule {

    @Provides
    @Singleton
    public RestClient provideDaoSession() {
        return Mockito.mock(RestClient.class);
    }
}
