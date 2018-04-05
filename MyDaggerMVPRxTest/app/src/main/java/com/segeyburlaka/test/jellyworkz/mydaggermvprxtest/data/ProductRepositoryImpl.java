package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.ProductLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

import static dagger.internal.Preconditions.checkNotNull;

@Singleton
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductsRemoteDataSource mProductsRemoteDataSource;
    private final ProductLocalDataSource mProductLocalDataSource;

    @Inject
    public ProductRepositoryImpl(
            @NonNull ProductsRemoteDataSource tasksRemoteDataSource,
            @NonNull ProductLocalDataSource tasksLocalDataSource

    ) {
        mProductsRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mProductLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    @Override
    public Observable<List<Product>> searchProducts(SearchParams searchParams) {
        return mProductsRemoteDataSource.searchProducts(searchParams);
    }

    @Override
    public Observable<List<Product>> getProductsBase() {
        return mProductLocalDataSource.getAllProducts();
    }

    @Override
    public void clearCash() {
        mProductLocalDataSource.deleteAll();
    }

    @Override
    public Observable<List<Product>> loadNextProducts(SearchParams searchParams) {
        return mProductsRemoteDataSource.searchProducts(searchParams);
    }

    @Override
    public Observable<Product> saveProduct(Product entity) {
        return mProductLocalDataSource.save(entity);
    }

    @Override
    public Observable<Product> deleteProduct(Product entity) {
        return mProductLocalDataSource.remove(entity);
    }
}
