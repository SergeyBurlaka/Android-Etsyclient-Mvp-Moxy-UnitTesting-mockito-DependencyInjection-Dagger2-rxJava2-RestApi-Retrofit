package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest;

import android.app.Application;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.AppComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.ApplicationModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.DaggerAppComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.CategoryRepositoryComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.CategoryRepositoryModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.ProductRepositoryComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.ProductRepositoryModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyEtsyAppApplication extends Application {

    protected static MyEtsyAppApplication mInstance;
    private AppComponent mAppComponent;

    private ProductRepositoryComponent mProductRepositoryComponent;
    private CategoryRepositoryComponent mCategoryRepositoryComponent;

    public static MyEtsyAppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //For custom fonts:
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.app_fonts))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        mInstance = this;
        mAppComponent = DaggerAppComponent
                .builder().applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * PRODUCTS
     */
    public ProductRepositoryComponent plusProductRepoComponent() {
        if (mProductRepositoryComponent == null) {
            mProductRepositoryComponent = mAppComponent.plusProductRepositoryComponent(new ProductRepositoryModule());
        }
        return mProductRepositoryComponent;
    }

    /**
     * CATEGORIES
     */
    public CategoryRepositoryComponent plusCategoriesRepoComponent() {
        if (mCategoryRepositoryComponent == null) {
            mCategoryRepositoryComponent = mAppComponent.plusCategoryRepositoryComponent(new CategoryRepositoryModule());
        }
        return mCategoryRepositoryComponent;
    }

    public void clearProductRepoComponent() {
        mProductRepositoryComponent = null;
    }

    public void clearCategoryRepoComponent() {
        mCategoryRepositoryComponent = null;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void setProductRepositoryComponent(ProductRepositoryComponent productRepositoryComponent) {
        mProductRepositoryComponent = productRepositoryComponent;
    }

    public void setCategoryRepositoryComponent(CategoryRepositoryComponent categoryRepositoryComponent) {
        mCategoryRepositoryComponent = categoryRepositoryComponent;
    }
}
