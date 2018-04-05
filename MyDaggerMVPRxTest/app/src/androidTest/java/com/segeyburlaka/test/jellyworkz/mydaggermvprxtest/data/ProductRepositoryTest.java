package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.ApplicationModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.DaggerAppTestComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.UserRepositoryTestModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.locale.ProductLocalDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.ProductsRemoteDataSource;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxJavaResetRule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.TestUtils.getProductTestList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProductRepositoryTest {

    private static final int FIRST_ITEM = 0;
    private static final int LOAD_ITEMS_LIMIT = 10;
    private static final Long TEST_CHILD_ID = 7L;
    @Rule
    public final RxJavaResetRule rxJavaResetRule = new RxJavaResetRule();
    @Inject
    ProductsRemoteDataSource mProductsRemoteDataSource;
    @Inject
    ProductLocalDataSource mProductLocalDataSource;
    @Inject
    ProductRepository mProductRepository;

    @Before
    public void setUp() throws Exception {
        DaggerAppTestComponent.builder()
                .applicationModule(new ApplicationModule(InstrumentationRegistry.getTargetContext().getApplicationContext()))
                .build().plusUserRepositoryTestComponent(new UserRepositoryTestModule()).inject(this);
    }

    @Test
    public void testLoadChild() {
        TestSubscriber<List<Product>> testSubscriber = TestSubscriber.create();
        final int CURRENT_OFFSET = FIRST_ITEM + 10;
        List<Product> testProduct = getProductTestList(CURRENT_OFFSET, LOAD_ITEMS_LIMIT);

        SearchParams searchParams = new SearchParams();
        ArrayList arrayList = new ArrayList();
        arrayList.add("Terminator");
        searchParams.setKeywords(arrayList);

        searchParams.setLimit(LOAD_ITEMS_LIMIT);
        searchParams.setOffset(CURRENT_OFFSET);
        when(mProductsRemoteDataSource.searchProducts(searchParams))
                .thenReturn(Observable.just(testProduct));

        Observable<List<Product>> observable =
                mProductRepository.searchProducts(searchParams);
        observable.subscribeOn(Schedulers.computation())
                .subscribe(testSubscriber);
        List<Product> ProductListFromBase = testSubscriber.getOnNextEvents().get(0);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        verify(mProductsRemoteDataSource).searchProducts(searchParams);

        assertEquals(ProductListFromBase, getProductTestList(CURRENT_OFFSET, LOAD_ITEMS_LIMIT));
        assertEquals(ProductListFromBase.size(), LOAD_ITEMS_LIMIT);//did save all ProductFields in base
    }

    @Test
    public void testGetChildById() {
        TestSubscriber<List<Product>> testSubscriber = TestSubscriber.create();
        List<Product> testingProduct = TestUtils.getProductTestList(2);

        when(mProductLocalDataSource.getAllProducts()).thenReturn(Observable.just(testingProduct));
        mProductRepository.getProductsBase().subscribeOn(Schedulers.computation()).subscribe(testSubscriber);

        List<Product> productFromBase = testSubscriber.getOnNextEvents().get(0);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        assertNotNull(productFromBase);
        assertEquals(productFromBase, testingProduct);

        assertEquals(productFromBase.size(), 2);

        assertEquals(productFromBase.get(0).getName(), "Sergey Burlaka");
        assertEquals(productFromBase.get(0).getDescription(), "Description");
    }
}
