package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list;

import android.content.Intent;
import android.os.Bundle;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.main.MainView;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.ActivityUtils;

public class ProductActivity extends BaseActivity implements
        MainView,
        ProductFragmentHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.search_product_result));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        SearchParams searchParams = null;
        if (extras != null) {
            searchParams = extras.getParcelable(SEARCH_PARAMS);
        }

        ProductFragment childDetailsFragment =
                (ProductFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (childDetailsFragment == null) {
            childDetailsFragment = ProductFragment.newInstance(searchParams);
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

    @Override
    public void showProductDetail(Product product) {
        Intent myIntent = new Intent(ProductActivity.this, com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details.ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_ENTITY, product);
        myIntent.putExtras(bundle);
        ProductActivity.this.startActivity(myIntent);
    }
}
