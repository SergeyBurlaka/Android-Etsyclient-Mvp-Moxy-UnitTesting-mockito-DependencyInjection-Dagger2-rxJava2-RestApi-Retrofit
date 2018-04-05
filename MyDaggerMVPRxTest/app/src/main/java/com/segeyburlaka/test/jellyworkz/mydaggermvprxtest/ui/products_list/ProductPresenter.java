package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list;

import android.graphics.Bitmap;

import com.arellomobile.mvp.InjectViewState;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BasePresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.ImageUtil.saveImage;


@InjectViewState
public class ProductPresenter extends BasePresenter<ProductView> {

    private final Set<Subscription> mSubscriptions = new HashSet<>();
    @Inject
    ProductRepository mProductRepository;

    ProductPresenter() {
        MyEtsyAppApplication.getInstance().plusProductRepoComponent().inject(this);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    //Load products in begin (offset = 0)
    void loadProducts(SearchParams searchParams) {
        if (searchParams == null) {
            getViewState().onLoadFailed(MyEtsyAppApplication.getInstance().getString(R.string.no_search_params_error));
        }
        addSubscription(
                mProductRepository.searchProducts(searchParams)
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(this::successLoaded, error -> getViewState().onLoadFailed(error.getMessage())));
    }

    //offset = n
    void loadNext(SearchParams searchParams) {
        if (searchParams == null) {
            getViewState().onLoadFailed(MyEtsyAppApplication.getInstance().getString(R.string.no_search_params_error));
        }
        addSubscription(
                mProductRepository.loadNextProducts(searchParams)
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(this::successNextLoaded,
                                error -> getViewState().onLoadNextFailed(error.getMessage())));
    }

    //reload products in begin by pull-to -refresh (offset = 0)
    void onRefresh(SearchParams searchParams) {
        if (searchParams == null) {
            getViewState().onLoadFailed(MyEtsyAppApplication.getInstance().getString(R.string.no_search_params_error));
        }
        addSubscription(
                mProductRepository.searchProducts(searchParams)
                        .compose(RxUtils.ioToMainTransformer())
                        .subscribe(products -> getViewState().successRefreshed(products),
                                error -> getViewState().onLoadFailed(error.getMessage())));
    }

    private void successLoaded(List<Product> products) {
        //do some business logic with data in feature
        getViewState().onLoadedSuccess(products);
    }

    private void successNextLoaded(List<Product> products) {
        //do some business logic with data in feature
        getViewState().onLoadedNextSuccess(products);
    }

    @Override
    public void onDestroy() {
        MyEtsyAppApplication.getInstance().clearProductRepoComponent();
    }

    private void updateFavoriteProducts(Product entity) {
        if (entity.getFavorite()) {
            addSubscription(mProductRepository.saveProduct(entity)
                    .compose(RxUtils.ioToMainTransformer())
                    .subscribe(product -> getViewState().successUpdateFavorite(product, true),
                            error -> getViewState().onLoadFailed(error.getMessage())));
        } else {
            addSubscription(mProductRepository.deleteProduct(entity)
                    .compose(RxUtils.ioToMainTransformer())
                    .subscribe(product -> getViewState().successUpdateFavorite(product, false),
                            error -> getViewState().onLoadFailed(error.getMessage())));

        }
    }

    void saveProductBase(Product product) {
        if (!product.getFavorite()) {
            updateFavoriteProducts(product);
            return;
        }
        Glide.with(MyEtsyAppApplication.getInstance())
                .load(product.getMainImage().getPhotoUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        addSubscription(Observable.just(resource)
                                .map(bitmap -> saveImage(bitmap, product.getId() + "_full"))
                                .compose(RxUtils.ioToMainTransformer())
                                .subscribe(pathFullPhoto -> {
                                    product.getMainImage().setPhotoUrl(pathFullPhoto);
                                    Glide.with(MyEtsyAppApplication.getInstance())
                                            .load(product.getMainImage().getThumbnailUrls())
                                            .asBitmap()
                                            .into(new SimpleTarget<Bitmap>(100, 100) {
                                                @Override
                                                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                    addSubscription(Observable.just(resource)
                                                            .map(bitmap -> saveImage(bitmap, product.getId() + "_tumb"))
                                                            .compose(RxUtils.ioToMainTransformer())
                                                            .subscribe(pathThumbnail -> {
                                                                        product.getMainImage().setThumbnailUrls(pathThumbnail);
                                                                        updateFavoriteProducts(product);
                                                                    }
                                                                    , error -> getViewState().onLoadFailed(error.getMessage())));
                                                }
                                            });
                                }, error -> getViewState().onLoadFailed(error.getMessage())));
                    }
                });
    }
}


