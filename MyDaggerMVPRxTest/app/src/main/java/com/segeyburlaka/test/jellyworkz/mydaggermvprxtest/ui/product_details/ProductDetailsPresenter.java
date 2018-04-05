package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details;


import android.graphics.Bitmap;

import com.arellomobile.mvp.InjectViewState;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BasePresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxUtils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.ImageUtil.saveImage;

@InjectViewState
public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    private final Set<Subscription> mSubscriptions = new HashSet<>();
    @Inject
    ProductRepository mProductRepository;

    ProductDetailsPresenter() {
        MyEtsyAppApplication.getInstance().plusProductRepoComponent().inject(this);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    void saveProductBase(Product product) {
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
                                                                        addSubscription(mProductRepository.saveProduct(product)
                                                                                .compose(RxUtils.ioToMainTransformer())
                                                                                .subscribe(productSaved -> getViewState().successUpdateFavorite(productSaved),
                                                                                        error -> getViewState().onLoadFailed(error.getMessage())));
                                                                    }
                                                                    , error -> getViewState().onLoadFailed(error.getMessage())));
                                                }
                                            });
                                }, error -> getViewState().onLoadFailed(error.getMessage())));
                    }
                });
    }

    @Override
    public void onDestroy() {
        MyEtsyAppApplication.getInstance().clearProductRepoComponent();
    }

    public void removeFromFavorite(Product product) {
        addSubscription(mProductRepository.deleteProduct(product)
                .compose(RxUtils.ioToMainTransformer())
                .subscribe(productRemoved -> getViewState().successRemovedFavorite(productRemoved),
                        error -> getViewState().onLoadFailed(error.getMessage())));
    }
}
