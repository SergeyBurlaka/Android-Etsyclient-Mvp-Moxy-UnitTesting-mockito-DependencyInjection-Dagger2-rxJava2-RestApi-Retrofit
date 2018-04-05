package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;


public interface BaseView<P extends MvpPresenter> extends MvpView {

    void showProgress();

    void hideProgress();
}
