package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;

import java.util.List;

import rx.Observable;

public interface ProductsRemoteDataSource {
    Observable<List<Product>> searchProducts(SearchParams searchParams);
}
