package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.DaoSession;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.ProductEntity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.ProductEntityDao;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.DataMapper;

import java.util.List;

import rx.Observable;


public class ProductLocalDataSourceImpl extends BaseLocalDataSource<Long, Product, ProductEntity>
        implements ProductLocalDataSource {

    public ProductLocalDataSourceImpl(@NonNull DaoSession daoSession,
                                      @NonNull DataMapper<Product, ProductEntity> userEntityDataMapper) {
        super(userEntityDataMapper, daoSession.getProductEntityDao().rx());
    }

    @Override
    public Observable<List<Product>> getAllProducts() {
        return getRxDao()
                .getDao()
                .queryBuilder()
                //.orderDesc(ChildEntityDao.Properties.TimeOfRequest)
                .rx()
                .list()
                .compose(getDataMapper().listFrom());
    }

    @Override
    public Observable<Void> deleteAllWithFavoriteFalse() {
        return getRxDao()
                .getDao()
                .queryBuilder()
                .where(ProductEntityDao.Properties.IsFavorite.eq(false))
                .rx()
                .list()
                .flatMap(comments -> getRxDao().deleteInTx(comments));
    }
}


