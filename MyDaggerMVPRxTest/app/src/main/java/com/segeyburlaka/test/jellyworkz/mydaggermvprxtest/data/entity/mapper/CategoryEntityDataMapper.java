package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.mapper;

import android.support.annotation.Nullable;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.CategoryEntity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.BaseDataMapper;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.DataMapper;


public class CategoryEntityDataMapper extends BaseDataMapper<Category, CategoryEntity>
        implements DataMapper<Category, CategoryEntity> {

    @Nullable
    @Override
    public CategoryEntity to(@Nullable Category transformFromObject) {
        if (transformFromObject == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(transformFromObject.getId());
        categoryEntity.setName(transformFromObject.getName());
        categoryEntity.setShortName(transformFromObject.getShortName());
        return categoryEntity;
    }

    @Nullable
    @Override
    public Category from(@Nullable CategoryEntity toObject) {
        if (toObject == null) {
            return null;
        }
        Category category = new Category();
        category.setId(toObject.getId());
        category.setName(toObject.getName());
        category.setShortName(toObject.getShortName());
        return category;
    }
}
