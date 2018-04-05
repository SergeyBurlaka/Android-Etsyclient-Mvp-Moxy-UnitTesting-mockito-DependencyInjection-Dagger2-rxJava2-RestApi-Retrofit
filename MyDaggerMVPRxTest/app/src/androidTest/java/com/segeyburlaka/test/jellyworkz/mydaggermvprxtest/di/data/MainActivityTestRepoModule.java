package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.ProductRepository;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityTestRepoModule {

    @Provides
    @RepositoryScope
    public ProductRepository provideProductRepository() {
        return Mockito.mock(ProductRepository.class);
    }
}
