package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.apies;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.NetworkContract;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.beans.GetCategoriesBean;

import retrofit2.http.GET;
import rx.Observable;

public interface CategoriesEtsyService {

    /**
     * Find All Top Category
     * URI:	/taxonomy/categories
     *
     * @see <a href="https://www.etsy.com/developers/documentation/reference/category#method_findalltopcategory">Etsy API method findAllTopCategory</a>
     */
    @GET("/" + NetworkContract.Etsy.API_VERSION + NetworkContract.CategoryAPIReference.FIND_ALL_TOP_CATEGORY + "?" +

            "api_key=" + NetworkContract.Etsy.API_KEY + "&fields=" + NetworkContract.CategoryParam.CATEGORY_FIELDS
    )
    Observable<GetCategoriesBean> findAllTopCategory();
}

