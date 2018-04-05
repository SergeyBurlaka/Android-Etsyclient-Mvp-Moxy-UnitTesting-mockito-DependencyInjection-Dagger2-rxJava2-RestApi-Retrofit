package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;

import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepositoryImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoSession;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.mapper.ProductEntityDataMapper;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.ProductLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.ProductLocalDataSourceImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSourceImpl;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link ProductRepositoryImpl}.
 */
@Module
public class ProductRepositoryModule {


    @Provides
    @RepositoryScope
    public ProductLocalDataSource provideProductLocalDataSource(@NonNull DaoSession daoSession
    ) {
        return new ProductLocalDataSourceImpl(daoSession, new ProductEntityDataMapper());
    }

    @Provides
    @RepositoryScope
    public ProductsRemoteDataSource provideProductRemoteDataSource(@NonNull RestClient restClient
    ) {
        return new ProductsRemoteDataSourceImpl(restClient);
    }

    @Provides
    @RepositoryScope
    public ProductRepository provideProductRepository(@NonNull ProductsRemoteDataSource productsRemoteDataSource,
                                                      @NonNull ProductLocalDataSource productLocalDataSource
    ) {
        return new ProductRepositoryImpl(
                productsRemoteDataSource, productLocalDataSource);
    }
}
