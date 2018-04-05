package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.RestClient;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.apies.ProductsListingEtsyService;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.GetProductsBean;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption.NetworkErrorUtils;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.preferences.PrefProvider;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.SearchStringUtil.getSearchString;

public class ProductsRemoteDataSourceImpl implements ProductsRemoteDataSource {

    private ProductsListingEtsyService mProductsListingEtsyService;

    @Inject
    public ProductsRemoteDataSourceImpl(RestClient restClient) {
        mProductsListingEtsyService = restClient.
                getRestClient().create(ProductsListingEtsyService.class);
    }

    @Override
    public Observable<List<Product>> searchProducts(SearchParams searchParams) {

        return mProductsListingEtsyService
                .findAllListingActive(searchParams.getCategory(), getSearchString(searchParams.getKeywords()),
                        searchParams.getOffset(), searchParams.getLimit()
                )
                .map(getProductsBean -> {
                    PrefProvider.getInstance().saveResultsCounter(getProductsBean.getCount());
                    return getProductsBean;
                })
                .map(getProductsBean -> getProductsBean)
                .map(GetProductsBean::getResults)
                .onErrorResumeNext(NetworkErrorUtils.instance().rxParseError());
    }
}

