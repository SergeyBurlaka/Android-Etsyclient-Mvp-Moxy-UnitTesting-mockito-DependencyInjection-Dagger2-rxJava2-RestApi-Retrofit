package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;

import java.util.List;

import rx.Observable;

public interface ProductRepository {
    Observable<List<Product>> searchProducts(SearchParams searchParams);

    Observable<List<Product>> getProductsBase();

    void clearCash();

    Observable<List<Product>> loadNextProducts(SearchParams searchParams);

    Observable<Product> saveProduct(Product entity);

    Observable<Product> deleteProduct(Product entity);
}
