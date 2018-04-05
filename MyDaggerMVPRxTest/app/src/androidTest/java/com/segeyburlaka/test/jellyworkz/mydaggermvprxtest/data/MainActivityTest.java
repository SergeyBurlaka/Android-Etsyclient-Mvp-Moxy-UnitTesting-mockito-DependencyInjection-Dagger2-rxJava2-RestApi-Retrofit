package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.ApplicationModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.app.DaggerAppTestComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.MainActivityTestRepoComponent;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data.MainActivityTestRepoModule;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption.ApiErrorException;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.data.remote.SearchParams;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.main.MainActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.products_list.ProductActivity;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.RxJavaResetRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.TestUtils.getProductTestList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityTest {

    protected static final String USER_ID = "PRODUCT_ENTITY";
    private static final int FIRST_ITEM = 0;
    private static final int LOAD_ITEMS_LIMIT = 10;
    private static final Long TEST_CHILD_ID = 7L;
    @Rule
    public final RxJavaResetRule rxJavaResetRule = new RxJavaResetRule();
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);
    @Rule
    public ActivityTestRule<ProductActivity> activityProductRule = new ActivityTestRule<>(ProductActivity.class, false, false);
    @Inject
    ProductRepository mProductRepository;
    private int offset = FIRST_ITEM;

    @Before
    public void setUp() throws Exception {
        MainActivityTestRepoComponent usersRepositoryTestComponent = DaggerAppTestComponent.builder()
                .applicationModule(new ApplicationModule(InstrumentationRegistry.getTargetContext().getApplicationContext()))
                .build().plusMainActivityTestRepoComponent(new MainActivityTestRepoModule());

        getAppFromInstrumentation().setProductRepositoryComponent(usersRepositoryTestComponent);
        usersRepositoryTestComponent.inject(this);
    }

    private MyEtsyAppApplication getAppFromInstrumentation() {
        return (MyEtsyAppApplication) getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.segeyburlaka.test.govjet.madaggermvprxtest.debug", appContext.getPackageName());
    }

    @Test
    public void testLoadProduct() {
        final int CURRENT_OFFSET = FIRST_ITEM;
        List<Product> testProduct = getProductTestList(CURRENT_OFFSET, LOAD_ITEMS_LIMIT);
        when(mProductRepository.getProductsBase())
                .thenReturn(Observable.just(testProduct));

        activityRule.launchActivity(null);
        verify(mProductRepository).getProductsBase();
    }

    @Test
    public void testProductRepositoryWhenMainActivitySatrtApiExceptionLoad() {
        final int CURRENT_OFFSET = FIRST_ITEM;
        List<Product> testProduct = getProductTestList(CURRENT_OFFSET, LOAD_ITEMS_LIMIT);

        SearchParams searchParams = new SearchParams();
        when(mProductRepository.searchProducts(searchParams))
                .thenReturn(Observable.error(new ApiErrorException(InstrumentationRegistry.getTargetContext().getApplicationContext().getString(R.string.api_exeption))));

        when(mProductRepository.getProductsBase()).thenReturn(Observable.just(testProduct));

        activityRule.launchActivity(null);

        verify(mProductRepository).getProductsBase();
    }
}