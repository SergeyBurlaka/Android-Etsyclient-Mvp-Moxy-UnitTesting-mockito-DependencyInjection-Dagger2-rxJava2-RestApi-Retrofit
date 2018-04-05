package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details;

import android.os.Bundle;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.ActivityUtils;

public class ProductDetailActivity extends BaseActivity implements ProductDetailHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.product_detail));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Bundle extras = getIntent().getExtras();
        Product product = null;
        Boolean isFavoriteProduct = false;
        if (extras != null) {
            product = extras.getParcelable(PRODUCT_ENTITY);
            isFavoriteProduct = extras.getBoolean(IS_FAVORITE_PRODUCT, false);
        }
        ProductDetailsFragment childDetailsFragment =
                (ProductDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (childDetailsFragment == null) {
            childDetailsFragment = ProductDetailsFragment.newInstance(product, isFavoriteProduct);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), childDetailsFragment, R.id.contentFrame, false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
