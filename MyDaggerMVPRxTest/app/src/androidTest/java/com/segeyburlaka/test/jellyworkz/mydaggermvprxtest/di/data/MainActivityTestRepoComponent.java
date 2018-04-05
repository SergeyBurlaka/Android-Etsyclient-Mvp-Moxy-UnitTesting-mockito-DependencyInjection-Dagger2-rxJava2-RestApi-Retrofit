package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.MainActivityTest;

import dagger.Subcomponent;


@Subcomponent(modules = {MainActivityTestRepoModule.class})
@RepositoryScope
public interface MainActivityTestRepoComponent extends ProductRepositoryComponent {

    ProductRepository getProductRepository();

    void inject(MainActivityTest mainActivityTest);
    // void inject(ProductRecyclerViewListTest ProductListTest);
}
