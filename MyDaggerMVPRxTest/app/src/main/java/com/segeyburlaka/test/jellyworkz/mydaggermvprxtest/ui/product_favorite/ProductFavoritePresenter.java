package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BasePresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxUtils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Subscription;


@InjectViewState
public class ProductFavoritePresenter extends BasePresenter<ProductFavoriteView> {

    private final Set<Subscription> mSubscriptions = new HashSet<>();
    @Inject
    ProductRepository mProductRepository;

    ProductFavoritePresenter() {
        MyEtsyAppApplication.getInstance().plusProductRepoComponent().inject(this);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    void loadProductsBase() {
        addSubscription(
                mProductRepository.getProductsBase()
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(products -> getViewState().onLoadedSuccess(products), error -> getViewState().onLoadFailed(error.getMessage())));
    }

    void onRefresh() {
        addSubscription(
                mProductRepository.getProductsBase()
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(products -> getViewState().successRefreshed(products),
                                error -> getViewState().onLoadFailed(error.getMessage())));
    }

    @Override
    public void onDestroy() {
        MyEtsyAppApplication.getInstance().clearProductRepoComponent();
    }

    void deleteProductFromFavorite(Product entity) {
        addSubscription(mProductRepository.deleteProduct(entity)
                .compose(RxUtils.ioToMainTransformer())
                .subscribe(product -> getViewState().successDeletedProduct(product),
                        error -> getViewState().onLoadFailed(error.getMessage())));

    }
}


