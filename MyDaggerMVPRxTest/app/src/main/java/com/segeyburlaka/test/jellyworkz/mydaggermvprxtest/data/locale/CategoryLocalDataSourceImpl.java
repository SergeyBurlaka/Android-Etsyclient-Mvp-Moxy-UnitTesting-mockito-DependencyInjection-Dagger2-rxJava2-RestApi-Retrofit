package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.CategoryEntity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoSession;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.DataMapper;


public class CategoryLocalDataSourceImpl extends BaseLocalDataSource<Long, Category, CategoryEntity>
        implements CategoryLocalDataSource {

    public CategoryLocalDataSourceImpl(@NonNull DaoSession daoSession,
                                       @NonNull DataMapper<Category, CategoryEntity> commentCommentEntityDataMapper) {
        super(commentCommentEntityDataMapper, daoSession.getCategoryEntityDao().rx());
    }

}


