package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.mapper;

import android.support.annotation.Nullable;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.entity.ProductEntity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.MainImage;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.BaseDataMapper;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper.DataMapper;


public class ProductEntityDataMapper extends BaseDataMapper<Product, ProductEntity>
        implements DataMapper<Product, ProductEntity> {

    @Nullable
    @Override
    public ProductEntity to(@Nullable Product transformFromObject) {
        if (transformFromObject == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(transformFromObject.getId());
        productEntity.setName(transformFromObject.getName());
        productEntity.setPrice(transformFromObject.getPrice());
        productEntity.setCurrencyCode(transformFromObject.getCurrencyCode());
        productEntity.setDescription(transformFromObject.getDescription());
        if (transformFromObject.getMainImage() != null) {
            productEntity.setThumbnailUrl(transformFromObject.getMainImage().getThumbnailUrls());
            productEntity.setPhotoUrl(transformFromObject.getMainImage().getPhotoUrl());
        }
        productEntity.setIsFavorite(transformFromObject.getFavorite());
        return productEntity;
    }

    @Nullable
    @Override
    public Product from(@Nullable ProductEntity toObject) {
        if (toObject == null) {
            return null;
        }
        Product product = new Product();
        product.setId(toObject.getId());
        product.setName(toObject.getName());
        product.setPrice(toObject.getPrice());
        product.setCurrencyCode(toObject.getCurrencyCode());
        product.setDescription(toObject.getDescription());
        product.setFavorite(toObject.getIsFavorite());
        MainImage image = new MainImage();
        image.setPhotoUrl(toObject.getPhotoUrl());
        image.setThumbnailUrls(toObject.getThumbnailUrl());
        product.setMainImage(image);
        return product;
    }
}
