package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseView;

import java.util.List;

interface SearchView extends BaseView<SearchPresenter> {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void onLoadedCategoriesSuccess(List<Category> categories);

    @StateStrategyType(SkipStrategy.class)
    void onLoadFailed(String message);
}
