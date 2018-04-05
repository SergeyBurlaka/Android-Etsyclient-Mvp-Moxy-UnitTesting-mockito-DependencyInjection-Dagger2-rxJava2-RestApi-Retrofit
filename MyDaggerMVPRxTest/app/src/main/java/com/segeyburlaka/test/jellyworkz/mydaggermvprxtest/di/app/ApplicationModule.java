package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public final class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}