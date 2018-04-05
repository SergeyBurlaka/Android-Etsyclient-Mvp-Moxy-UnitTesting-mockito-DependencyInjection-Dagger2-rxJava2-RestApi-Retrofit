package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.product_favorite;


import android.support.annotation.NonNull;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list.ProductAdapter;

import java.util.List;

class ProductFavoriteAdapter extends ProductAdapter {

    ProductFavoriteAdapter(@NonNull ProductAdapter.AdapterCallback view, @NonNull List<Product> mUsers) {
        super(view, mUsers);
    }

    ProductFavoriteAdapter(@NonNull ProductAdapter.AdapterCallback view) {
        super(view);
    }
}
