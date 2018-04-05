package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;

public class SnackBar {

    private static final int SNACK_BAR_DURATION = 5000;

    private InputMethodManager inputMethodManager;

    @Nullable
    private SnackBarHelper mSnackBarHelper;

    public SnackBar() {
        inputMethodManager = (InputMethodManager) MyEtsyAppApplication.getInstance()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void setSnackBarHelper(@Nullable SnackBarHelper snackBarHelper) {
        mSnackBarHelper = snackBarHelper;
    }

    public void showSnackBar(String text) {
        if (mSnackBarHelper == null) {
            return;
        }
        hideKeyboard();
        Snackbar snackbar = Snackbar.make(mSnackBarHelper.getRootView(), text, SNACK_BAR_DURATION);
        setUpSnackBarView(snackbar.getView(), snackbar);
        snackbar.show();
    }

    public void showSnackBar(@StringRes int textId) {
        if (mSnackBarHelper == null) {
            return;
        }
        hideKeyboard();
        Snackbar snackbar = Snackbar.make(mSnackBarHelper.getRootView(), textId, SNACK_BAR_DURATION);
        setUpSnackBarView(snackbar.getView(), snackbar);
        snackbar.show();
    }

    private void setUpSnackBarView(View snackBarView, Snackbar snackbar) {
        snackBarView.setOnClickListener(v -> snackbar.dismiss());
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        snackBarView.setBackgroundResource(R.color.colorAccent);
        textView.setMaxLines(5);
    }

    public void hideKeyboard() {
        if (mSnackBarHelper == null) {
            return;
        }
        View view = mSnackBarHelper.getContext().getCurrentFocus();
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public interface SnackBarHelper {
        View getRootView();

        Activity getContext();
    }
}