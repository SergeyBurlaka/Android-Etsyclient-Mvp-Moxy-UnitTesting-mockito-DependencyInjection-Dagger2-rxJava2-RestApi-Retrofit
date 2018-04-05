package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment;


import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.CategoryRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BasePresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxUtils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> {

    private final Set<Subscription> mSubscriptions = new HashSet<>();

    @Inject
    CategoryRepository mCategoryRepository;

    SearchPresenter() {
        MyEtsyAppApplication.getInstance().plusCategoriesRepoComponent().inject(this);
    }

    void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    void loadCategories() {
        addSubscription(
                mCategoryRepository.loadCategories()
                        .onErrorResumeNext(mCategoryRepository.loadCategoriesBase())
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(categories -> getViewState().onLoadedCategoriesSuccess(categories),
                                error -> getViewState().onLoadFailed(error.getMessage())));
    }

    @Override
    public void onDestroy() {
        MyEtsyAppApplication.getInstance().clearCategoryRepoComponent();
    }
}
