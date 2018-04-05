package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.base_mapper;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.Observable;

/**
 * @param <From> Input type
 * @param <To>   Output type
 */
public interface DataMapper<From, To> {

    /**
     * @param transformFromObject object ot convertIterableToList
     * @return converted object
     */
    @Nullable
    To to(@Nullable From transformFromObject);

    @Nullable
    Observable<To> transformToRx(@Nullable From fromObject);

    /**
     * @param toObject object to convertIterableToList
     * @return converted object
     */
    @Nullable
    From from(@Nullable To toObject);

    @Nullable
    Observable<From> fromRx(@Nullable To toObject);

    /**
     * @param fromObjects {@link List} of objects to convertIterableToList
     * @return {@link List} of converted objects
     */
    @NonNull
    List<To> listTo(@Nullable List<From> fromObjects);

    @NonNull
    Observable<List<To>> transformToRx(@Nullable List<From> fromObjects);

    /**
     * @param toObjects {@link List} of objects to convertIterableToList
     * @return {@link List} of converted objects
     */
    @NonNull
    List<From> listFrom(@Nullable List<To> toObjects);

    @NonNull
    Observable<List<From>> listFromRx(@Nullable List<To> toObjects);

    /**
     * @return Instance of {@link Observable.Transformer}
     */
    Observable.Transformer<To, From> singleFrom();

    /**
     * @return Instance of {@link Observable.Transformer}
     */
    Observable.Transformer<From, To> singleTo();

    /**
     * @return Instance of {@link Observable.Transformer}
     */
    Observable.Transformer<List<To>, List<From>> listFrom();

    /**
     * @return Instance of {@link Observable.Transformer}
     */
    Observable.Transformer<List<From>, List<To>> listTo();
}
