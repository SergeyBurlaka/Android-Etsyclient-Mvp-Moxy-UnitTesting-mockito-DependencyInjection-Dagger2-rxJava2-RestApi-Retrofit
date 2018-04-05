package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseView;

import java.util.List;


interface ProductFavoriteView extends BaseView<ProductFavoritePresenter> {

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadFailed(String errorMessage);

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadedSuccess(List<Product> results);

    @StateStrategyType(SingleStateStrategy.class)
    void successRefreshed(List<Product> products);

    @StateStrategyType(SkipStrategy.class)
    void successDeletedProduct(Product product);
}
