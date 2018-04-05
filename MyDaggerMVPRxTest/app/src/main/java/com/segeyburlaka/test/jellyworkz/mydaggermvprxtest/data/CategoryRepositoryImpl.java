package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.CategoryLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.CategoryRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

import static dagger.internal.Preconditions.checkNotNull;

@Singleton
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryRemoteDataSource mCategoryRemoteDataSource;
    private final CategoryLocalDataSource mCategoryLocalDataSource;

    @Inject
    public CategoryRepositoryImpl(
            @NonNull CategoryRemoteDataSource categoryRemoteDataSource,
            @NonNull CategoryLocalDataSource categoryLocalDataSource

    ) {
        mCategoryRemoteDataSource = checkNotNull(categoryRemoteDataSource);
        mCategoryLocalDataSource = checkNotNull(categoryLocalDataSource);
    }

    @Override
    public Observable<List<Category>> loadCategories() {
        return mCategoryRemoteDataSource
                .getAllTopCategories()
                .flatMap(comments -> mCategoryLocalDataSource.deleteAll().map(ignore -> comments))
                .flatMap(mCategoryLocalDataSource::saveList);
    }

    @Override
    public Observable<List<Category>> loadCategoriesBase() {
        return mCategoryLocalDataSource.getAll();
    }
}
