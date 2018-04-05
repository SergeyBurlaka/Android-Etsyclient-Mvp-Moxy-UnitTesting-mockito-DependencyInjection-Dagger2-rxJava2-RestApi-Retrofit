package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details;


import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseView;

interface ProductDetailsView extends BaseView<ProductDetailsPresenter> {
    @StateStrategyType(SkipStrategy.class)
    void successUpdateFavorite(Product productSaved);

    @StateStrategyType(SkipStrategy.class)
    void onLoadFailed(String message);

    @StateStrategyType(SkipStrategy.class)
    void successRemovedFavorite(Product productRemoved);
}
