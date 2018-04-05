package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseFragment;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite.OnChangedFavoriteProductsBaseEvent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.GlideUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.CurrencyUtils.getPrice;


public class ProductDetailsFragment extends BaseFragment<ProductDetailsPresenter> implements
        ProductDetailsView, EasyPermissions.PermissionCallbacks {

    private static final String EXTRA_PRODUCT_ENTITY = "EXTRA_PRODUCT_ENTITY";
    private static final String CHILD_ENTITY_STATE = "childEntity";
    private static final String EXTRA_IS_FAVORITE = "isFavoriteProduct";
    private static final String IS_FAVORITE_STATE = "isFavoriteState";
    private static final String[] write_permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private final static int RC_WRITE_EXTERNAL_STORAGE = 102;
    @InjectPresenter
    ProductDetailsPresenter mProductDetailsPresenter;
    @BindView(R.id.tv_index)
    TextView tvProductIndex;
    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R.id.tv_product_description)
    TextView tvProductDescription;
    @BindView(R.id.iv_photo_product)
    ImageView ivProductImage;
    @BindView(R.id.b_update_product_status)
    Button bUpdateProductStatus;
    private ProductDetailHost mMainUserDetailsView;
    private Product mProduct;
    ;
    private boolean isFavoriteProduct;

    public static ProductDetailsFragment newInstance(Product product, Boolean isFavoriteProduct) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_PRODUCT_ENTITY, product);
        args.putBoolean(EXTRA_IS_FAVORITE, isFavoriteProduct);
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductDetailHost) {
            mMainUserDetailsView = (ProductDetailHost) context;
        }
    }

    @Override
    public void onDetach() {
        mMainUserDetailsView = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProduct = getArguments().getParcelable(EXTRA_PRODUCT_ENTITY);
        isFavoriteProduct = getArguments().getBoolean(EXTRA_IS_FAVORITE, false);
        if (savedInstanceState != null) {
            mProduct = savedInstanceState.getParcelable(CHILD_ENTITY_STATE);
            isFavoriteProduct = savedInstanceState.getBoolean(IS_FAVORITE_STATE);
        }
        if (isFavoriteProduct) {
            bUpdateProductStatus.setText(R.string.button_title_detail_product);
        }
        showProductData(mProduct);
    }

    @OnClick(R.id.b_update_product_status)
    protected void onClickSave() {
        if (mProduct != null) {
            if (isFavoriteProduct) {
                mProductDetailsPresenter.removeFromFavorite(mProduct);
            } else {
                onWrightPermissions();
            }
        }
    }

    public void showProductData(Product product) {
        if (product == null) {
            return;
        }
        mProduct = product;
        tvProductIndex.setText("#");
        tvProductIndex.append(String.valueOf(product.getId()));
        tvProductTitle.setText(product.getName());
        tvProductDescription.setText(product.getDescription());
        if (product.getPrice() != null) {
            tvProductPrice.setText(getPrice(Double.parseDouble(product.getPrice()), product.getCurrencyCode()));
        }

        GlideUtils.loadImage(
                ivProductImage,
                product.getMainImage() != null/*NPE check(!)*/ ? product.getMainImage().getPhotoUrl() : "drawable://" + R.drawable.image_placeholder,
                R.drawable.image_placeholder);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mProduct != null) {
            outState.putParcelable(CHILD_ENTITY_STATE, mProduct);
            outState.putBoolean(IS_FAVORITE_STATE, isFavoriteProduct);
        }
    }

    @Override
    public void successUpdateFavorite(Product productSaved) {
        EventBus.getDefault().postSticky(new OnChangedFavoriteProductsBaseEvent());
        showSnackBar(getString(R.string.success_base_operation_message));
    }

    @Override
    public void onLoadFailed(String message) {
        showSnackBar(message);
    }

    @Override
    public void successRemovedFavorite(Product productRemoved) {
        EventBus.getDefault().postSticky(new OnChangedFavoriteProductsBaseEvent());
        getActivity().finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_WRITE_EXTERNAL_STORAGE)
    private void onWrightPermissions() {
        if (!EasyPermissions.hasPermissions(getActivity(), write_permissions)) {
            EasyPermissions.requestPermissions(this,
                    "This sample will write and read you extnernal storage",
                    RC_WRITE_EXTERNAL_STORAGE, write_permissions);
            return;
        } else {
            mProduct.setFavorite(true);
            mProductDetailsPresenter.saveProductBase(mProduct);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        mProduct.setFavorite(true);
        mProductDetailsPresenter.saveProductBase(mProduct);
    }
}


