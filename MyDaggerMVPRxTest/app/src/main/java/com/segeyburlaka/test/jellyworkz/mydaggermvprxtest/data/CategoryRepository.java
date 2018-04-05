package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;

import java.util.List;

import rx.Observable;

public interface CategoryRepository {
    Observable<List<Category>> loadCategories();

    Observable<List<Category>> loadCategoriesBase();
}
