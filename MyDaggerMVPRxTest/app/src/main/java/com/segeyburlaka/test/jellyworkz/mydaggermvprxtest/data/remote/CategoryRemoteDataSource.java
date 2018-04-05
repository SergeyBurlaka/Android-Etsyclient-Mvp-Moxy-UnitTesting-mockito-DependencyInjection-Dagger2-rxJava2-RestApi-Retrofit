package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;

import java.util.List;

import rx.Observable;

public interface CategoryRemoteDataSource {
    Observable<List<Category>> getAllTopCategories();
}
