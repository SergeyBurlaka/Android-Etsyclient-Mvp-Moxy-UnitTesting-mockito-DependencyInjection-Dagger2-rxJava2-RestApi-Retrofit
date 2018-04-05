package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepositoryTest;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.MainActivityTest;

import dagger.Subcomponent;


@Subcomponent(modules = {UserRepositoryTestModule.class})
@RepositoryScope
public interface ProductRepositoryTestComponent extends ProductRepositoryComponent {

    ProductRepository getProductRepository();

    void inject(ProductRepositoryTest usersRepositoryTest);

    void inject(MainActivityTest mainActivityTest);
}
