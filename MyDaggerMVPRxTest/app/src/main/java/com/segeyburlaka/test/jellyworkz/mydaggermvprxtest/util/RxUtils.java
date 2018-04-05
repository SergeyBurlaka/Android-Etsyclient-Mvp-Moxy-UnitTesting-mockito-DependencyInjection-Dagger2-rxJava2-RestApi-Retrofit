package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxUtils {

    private RxUtils() {
        //no instance
    }

    public static <T> Observable.Transformer<T, T> ioToMainTransformer() {
        return inObservable -> inObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
