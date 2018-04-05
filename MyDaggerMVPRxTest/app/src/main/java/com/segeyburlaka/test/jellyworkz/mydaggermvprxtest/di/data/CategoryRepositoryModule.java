package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;

import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.CategoryRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.CategoryRepositoryImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoSession;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.mapper.CategoryEntityDataMapper;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.CategoryLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.CategoryLocalDataSourceImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.CategoryRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.CategoryRemoteDataSourceImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link CategoryRepositoryImpl}.
 */
@Module
public class CategoryRepositoryModule {


    @Provides
    @RepositoryScope
    public CategoryLocalDataSource provideCategoryLocalDataSource(@NonNull DaoSession daoSession
    ) {
        return new CategoryLocalDataSourceImpl(daoSession, new CategoryEntityDataMapper());
    }

    @Provides
    @RepositoryScope
    public CategoryRemoteDataSource provideCategoryRemoteDataSource(@NonNull RestClient restClient
    ) {
        return new CategoryRemoteDataSourceImpl(restClient);
    }

    @Provides
    @RepositoryScope
    public CategoryRepository provideCategoryRepository(@NonNull CategoryRemoteDataSource categoryRemoteDataSource,
                                                        @NonNull CategoryLocalDataSource categoryLocalDataSource
    ) {
        return new CategoryRepositoryImpl(
                categoryRemoteDataSource, categoryLocalDataSource);
    }
}
