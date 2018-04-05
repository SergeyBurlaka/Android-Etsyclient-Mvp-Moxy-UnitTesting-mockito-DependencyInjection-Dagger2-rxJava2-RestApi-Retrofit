package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base.BaseViewPagerAdapter;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_details.ProductDetailActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite.ProductFavoriteFragment;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite.ProductFavoriteFragmentHost;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list.ProductActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment.SearchFragment;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.search_fragment.SearchFragmentHost;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        MainView,
        SearchFragmentHost,
        ProductFavoriteFragmentHost {

    private BaseViewPagerAdapter mPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.search_etsy));
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = initAdapter();
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showProductDetail(Product product) {
        Intent myIntent = new Intent(MainActivity.this, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_ENTITY, product);
        bundle.putBoolean(IS_FAVORITE_PRODUCT, true);
        myIntent.putExtras(bundle);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void showProductFragment(SearchParams searchParams) {
        Intent myIntent = new Intent(MainActivity.this, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SEARCH_PARAMS, searchParams);
        myIntent.putExtras(bundle);
        MainActivity.this.startActivity(myIntent);
    }

    private void showSearchFragment() {
        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.class.getName());
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), searchFragment, R.id.contentFrame, true);
        }
    }

    private void showProductFavorite() {
        ProductFavoriteFragment searchFragment = (ProductFavoriteFragment) getSupportFragmentManager().findFragmentByTag(ProductFavoriteFragment.class.getName());
        if (searchFragment == null) {
            searchFragment = ProductFavoriteFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), searchFragment, R.id.contentFrame, true);
        }
    }

    protected BaseViewPagerAdapter initAdapter() {
        List<BaseViewPagerAdapter.FragmentInfoContainer> infoContainers = new ArrayList<>(3);
        {
            //ADD ACCOUNT TAB WITH POSITION = 0
            BaseViewPagerAdapter.FragmentInfoContainer container = BaseViewPagerAdapter
                    .newFragmentInfoContainer(SearchFragment.class, getString(R.string.etsy_saerch_tab), null);

            infoContainers.add(container);
        }

        {
            //ADD TRANSACTION TAB WITH POSITION = 1
            BaseViewPagerAdapter.FragmentInfoContainer container = BaseViewPagerAdapter
                    .newFragmentInfoContainer(ProductFavoriteFragment.class, getString(R.string.favorite_tab), null);

            infoContainers.add(container);
        }

        return new BaseViewPagerAdapter(this, getSupportFragmentManager(), infoContainers);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment != null && fragment instanceof SearchFragment) {
            this.finish();
        }
        super.onBackPressed();
    }
}
