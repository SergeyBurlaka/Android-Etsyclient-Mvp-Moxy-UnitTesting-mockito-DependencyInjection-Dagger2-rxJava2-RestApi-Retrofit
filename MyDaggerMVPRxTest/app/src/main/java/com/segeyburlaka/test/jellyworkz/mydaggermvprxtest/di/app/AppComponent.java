package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.CategoryRepositoryComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.CategoryRepositoryModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.ProductRepositoryComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.ProductRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, GreenDaoModule.class, RetrofitModule.class})

public interface AppComponent {

    ProductRepositoryComponent plusProductRepositoryComponent(ProductRepositoryModule productRepositoryModule);

    CategoryRepositoryComponent plusCategoryRepositoryComponent(CategoryRepositoryModule categoryRepositoryModule);

}
