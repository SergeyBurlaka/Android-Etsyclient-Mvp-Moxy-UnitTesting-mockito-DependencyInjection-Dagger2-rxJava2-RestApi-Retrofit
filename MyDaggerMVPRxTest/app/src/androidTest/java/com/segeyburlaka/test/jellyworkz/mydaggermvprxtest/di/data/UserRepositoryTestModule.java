package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;

import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepositoryImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.ProductLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSourceImpl;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryTestModule {

    @Provides
    @RepositoryScope
    public ProductsRemoteDataSource provideUserRemoteDataSource() {
        return Mockito.mock(ProductsRemoteDataSourceImpl.class);
    }

    @Provides
    @RepositoryScope
    public ProductLocalDataSource provideUserLocalDataSource() {
        return Mockito.mock(ProductLocalDataSource.class);
    }

    @Provides
    @RepositoryScope
    public ProductRepository provideUserRepository(@NonNull ProductsRemoteDataSource tasksRemoteDataSource,
                                                   @NonNull ProductLocalDataSource tasksLocalDataSource
    ) {
        return new ProductRepositoryImpl(
                tasksRemoteDataSource, tasksLocalDataSource);
    }
}
