package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;

import java.util.List;

import rx.Observable;

public interface ProductLocalDataSource extends LocalDataSource<Long, Product> {

    Observable<List<Product>> getAllProducts();

    Observable<Void> deleteAllWithFavoriteFalse();
}
