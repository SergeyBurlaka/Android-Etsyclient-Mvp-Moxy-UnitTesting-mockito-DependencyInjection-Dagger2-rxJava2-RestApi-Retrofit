package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Base implementation of {@link DataMapper}
 *
 * @param <From> Input type
 * @param <To>   Output type
 */
public abstract class BaseDataMapper<From, To> implements DataMapper<From, To> {

    @NonNull
    @Override
    public List<To> listTo(@Nullable List<From> fromObjects) {
        List<To> toList = new ArrayList<>();
        if (fromObjects == null) {
            return toList;
        }
        for (From from : fromObjects) {
            toList.add(to(from));
        }
        return toList;
    }

    @NonNull
    @Override
    public List<From> listFrom(@Nullable List<To> toObjects) {
        List<From> fromList = new ArrayList<>();
        if (toObjects == null) {
            return fromList;
        }
        for (To to : toObjects) {
            fromList.add(from(to));
        }
        return fromList;
    }

    @Nullable
    @Override
    public Observable<From> fromRx(@Nullable To toObject) {
        return Observable.just(from(toObject));
    }

    @Nullable
    @Override
    public Observable<To> transformToRx(@Nullable From fromObject) {
        return Observable.just(to(fromObject));
    }

    @NonNull
    @Override
    public Observable<List<To>> transformToRx(@Nullable List<From> fromObjects) {
        return Observable.just(listTo(fromObjects));
    }

    @NonNull
    @Override
    public Observable<List<From>> listFromRx(@Nullable List<To> toObjects) {
        return Observable.just(listFrom(toObjects));
    }

    @Override
    public Observable.Transformer<To, From> singleFrom() {
        return outObservable -> outObservable.map(BaseDataMapper.this::from);
    }

    @Override
    public Observable.Transformer<From, To> singleTo() {
        return inObservable -> inObservable.map(BaseDataMapper.this::to);
    }

    @Override
    public Observable.Transformer<List<To>, List<From>> listFrom() {
        return outListObservable -> outListObservable.map(this::listFrom);
    }

    @Override
    public Observable.Transformer<List<From>, List<To>> listTo() {
        return inListObservable -> inListObservable.map(this::listTo);
    }
}
