package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details.ProductDetailsPresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite.ProductFavoritePresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list.ProductPresenter;

import dagger.Subcomponent;


@Subcomponent(modules = {ProductRepositoryModule.class})
@RepositoryScope
public interface ProductRepositoryComponent {

    ProductRepository getChildrenRepository();

    void inject(ProductPresenter productPresenter);

    void inject(ProductDetailsPresenter productDetailsPresenter);

    void inject(ProductFavoritePresenter productFavoritePresenter);
}
