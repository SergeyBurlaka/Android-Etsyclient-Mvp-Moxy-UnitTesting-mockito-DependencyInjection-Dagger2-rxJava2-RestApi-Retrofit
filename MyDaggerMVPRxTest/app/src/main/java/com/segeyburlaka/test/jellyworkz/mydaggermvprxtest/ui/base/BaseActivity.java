package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends MvpAppCompatActivity implements ProgressView {

    protected static final String PRODUCT_ENTITY = "ProductEntity";
    protected static final String SEARCH_PARAMS = "SearchParams";
    protected static final String IS_FAVORITE_PRODUCT = "isFavoriteProduct";

    @Override
    protected void attachBaseContext(Context newBase) {
        //Inject Calligraphy into Context for adding custom fonts.
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void showProgress() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ProgressDialog.class.getName());
        if (fragment == null) {
            fragment = ProgressDialog.newInstance();
        }
        if (fragment instanceof DialogFragment) {
            DialogFragment dialog = (DialogFragment) fragment;
            if (dialog.isAdded()) {
                dialog.dismiss();
                ProgressDialog.newInstance().show(getSupportFragmentManager(), ProgressDialog.class.getName());
            } else {
                dialog.show(getSupportFragmentManager(), ProgressDialog.class.getName());
            }
        }
    }

    @Override
    public void hideProgress() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ProgressDialog.class.getName());
        if (fragment != null && fragment instanceof DialogFragment) {
            DialogFragment dialog = (DialogFragment) fragment;
            dialog.dismiss();
        }
    }
}
