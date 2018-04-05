package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.ProductRepositoryTestComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.MainActivityTestRepoComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.MainActivityTestRepoModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.UserRepositoryTestModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, GreenDaoModule.class, RetrofitTestModule.class})

public interface AppTestComponent extends AppComponent {

    ProductRepositoryTestComponent plusUserRepositoryTestComponent(UserRepositoryTestModule userRepositoryModule);

    MainActivityTestRepoComponent plusMainActivityTestRepoComponent(MainActivityTestRepoModule mainActivityTestRepoModule);

}
