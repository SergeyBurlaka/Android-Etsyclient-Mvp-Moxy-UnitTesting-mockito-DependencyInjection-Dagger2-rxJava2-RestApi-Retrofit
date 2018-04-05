package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.BuildConfig;

/**
 * Network contract.
 */
public final class NetworkContract {

    private NetworkContract() {
        //no instance
    }

    public static final class Base {
        public static final int TIMEOUT = 30;
        private static final String ENDPOINT = BuildConfig.ENDPOINT;
        public static final String API_ENDPOINT = ENDPOINT;

        private Base() {
            //no instance
        }
    }

    public static final class Etsy {
        public static final String API_KEY = BuildConfig.API_KEY;
        public static final String API_VERSION = "v2";

        private Etsy() {
            //no instance
        }
    }

    public static final class ListingsAPIReference {
        private static final String LISTINGS = "listings";
        private static final String LISTINGS_STATE_ACTIVE = "active";
        public static final String FIND_ALL_LISTING_ACTIVE = "/" + LISTINGS + "/" + LISTINGS_STATE_ACTIVE;

        private ListingsAPIReference() {
            //no instance
        }
    }

    public static final class CategoryAPIReference {
        private static final String TAXONOMY = "taxonomy";
        private static final String CATEGORIES = "categories";
        ///taxonomy/categories
        public static final String FIND_ALL_TOP_CATEGORY = "/" + TAXONOMY + "/" + CATEGORIES;

        private CategoryAPIReference() {
            //no instance
        }
    }

    public static final class CategoryParam {
        private static final String CATEGORY_ID = "category_id";
        private static final String NAME = "name";
        private static final String SHORT_NAME = "short_name";
        public static final String CATEGORY_FIELDS = CATEGORY_ID + "," + NAME + "," + SHORT_NAME;

        private CategoryParam() {
            //no instance
        }
    }

    public static final class ProductParam {

        private static final String LISTING_ID = "listing_id";
        private static final String TITLE = "title";
        private static final String PRICE = "price";
        private static final String CURRENCY_CODE = "currency_code";
        private static final String DESCRIPTION = "description";
        public static final String PRODUCT_FIELDS = LISTING_ID + "," + TITLE + "," + DESCRIPTION + "," + PRICE + "," + CURRENCY_CODE;

        private static final String MAIN_IMAGE = "MainImage";
        private static final String THUMBNAIL_SIZE = "url_170x135";
        private static final String FULL_SIZE = "url_570xN";
        public static final String IMAGE_INCLUDE = MAIN_IMAGE + "(" + THUMBNAIL_SIZE + "," + FULL_SIZE + ")";

        private ProductParam() {
            //no instance
        }
    }

    public static final class LanguageParam {
        public static final String LANGUAGE_RU = "ru";

        private LanguageParam() {
            //no instance
        }
    }
}
