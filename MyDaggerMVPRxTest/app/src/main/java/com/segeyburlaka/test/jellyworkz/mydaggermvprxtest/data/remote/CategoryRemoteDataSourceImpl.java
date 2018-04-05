package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Category;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.apies.CategoriesEtsyService;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.GetCategoriesBean;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption.NetworkErrorUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class CategoryRemoteDataSourceImpl implements CategoryRemoteDataSource {

    private CategoriesEtsyService mCommentService;

    @Inject
    public CategoryRemoteDataSourceImpl(RestClient restClient) {
        mCommentService = restClient.
                getRestClient().create(CategoriesEtsyService.class);
    }

    @Override
    public Observable<List<Category>> getAllTopCategories() {
        return mCommentService
                .findAllTopCategory()
                .map(GetCategoriesBean::getResults)
                .onErrorResumeNext(NetworkErrorUtils.instance().rxParseError());
    }
}
