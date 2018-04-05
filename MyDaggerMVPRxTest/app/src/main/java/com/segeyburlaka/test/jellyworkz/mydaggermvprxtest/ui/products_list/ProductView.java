package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseView;

import java.util.List;


interface ProductView extends BaseView<ProductPresenter> {

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadFailed(String errorMessage);

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadNextFailed(String message);

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadedSuccess(List<Product> results);

    @StateStrategyType(AddToEndStrategy.class)
    void onLoadedNextSuccess(List<Product> products);

    @StateStrategyType(SingleStateStrategy.class)
    void successRefreshed(List<Product> products);

    @StateStrategyType(SkipStrategy.class)
    void interruptLoading();

    @StateStrategyType(SkipStrategy.class)
    void successUpdateFavorite(Product product, boolean isAdd);

}
