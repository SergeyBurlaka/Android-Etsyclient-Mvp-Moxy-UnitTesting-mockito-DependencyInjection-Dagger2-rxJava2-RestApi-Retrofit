package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpPresenter;


public abstract class BaseFragment<P extends MvpPresenter> extends MvpAppCompatFragment implements
        BaseView<P>,
        SnackBar.SnackBarHelper {

    private static int sRequestCode = 1;
    protected static final int REQUEST_PLAY_SERVICE = getRequestCode();

    @Nullable
    private ProgressView mProgressView;
    private SnackBar mSnackBar;

    private static int getRequestCode() {
        return sRequestCode++;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSnackBar = new SnackBar();
        if (context instanceof ProgressView) {
            mProgressView = (ProgressView) context;
        }
        hideProgress();
    }

    @Override
    public void onDetach() {
        mProgressView = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        mSnackBar = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSnackBar.setSnackBarHelper(this);
    }

    @Override
    public void onPause() {
        mSnackBar.setSnackBarHelper(null);
        super.onPause();
    }

    public void showSnackBar(String text) {
        mSnackBar.showSnackBar(text);
    }

    @Override
    public void showProgress() {
        if (mProgressView != null) {
            mProgressView.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressView != null) {
            mProgressView.hideProgress();
        }
    }

    @Override
    public View getRootView() {
        return getView();
    }

    @Override
    public Activity getContext() {
        return getActivity();
    }
}
