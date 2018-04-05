package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.apies;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.NetworkContract;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.GetProductsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ProductsListingEtsyService {

    /**
     * Find All Listing Active
     * URI:	/listings/active
     *
     * @see <a href="https://www.etsy.com/developers/documentation/reference/listing#method_findalllistingactive">Etsy API method findAllListingActive</a>
     */
    @GET("/" + NetworkContract.Etsy.API_VERSION + NetworkContract.ListingsAPIReference.FIND_ALL_LISTING_ACTIVE + "?" +

            "api_key=" + NetworkContract.Etsy.API_KEY +

            "&fields=" + NetworkContract.ProductParam.PRODUCT_FIELDS +

            "&includes=" + NetworkContract.ProductParam.IMAGE_INCLUDE
    )
    Observable<GetProductsBean> findAllListingActive(@Query("category") String category,
                                                     @Query("keywords") String keywords,
                                                     @Query("offset") Integer offset,
                                                     @Query("limit") Integer limit);
}
