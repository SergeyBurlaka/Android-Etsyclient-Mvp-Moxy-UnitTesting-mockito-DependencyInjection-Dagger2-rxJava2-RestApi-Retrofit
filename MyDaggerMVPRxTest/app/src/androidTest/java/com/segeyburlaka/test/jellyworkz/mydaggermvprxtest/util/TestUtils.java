package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.v7.widget.Toolbar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.MainImage;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.models.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

/**
 * Useful test methods common to all activities
 */
public class TestUtils {

    @JsonProperty("listing_id")
    private Long mId; //listing_id
    @JsonProperty("title")
    private String mName;
    @JsonProperty("price")
    private String mPrice;
    @JsonProperty("currency_code")
    private String mCurrencyCode;
    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("MainImage")
    private MainImage mMainImage;
    @JsonIgnore
    private Boolean isFavorite = false;

    private TestUtils() {
        //no instance
    }

    public static Product getProductTest(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Sergey Burlaka");
        product.setDescription("Description");
        return product;
    }

    public static List<Product> getProductTestList(int limit) {
        List<Product> productListFromNet = new ArrayList<>();
        Product product;
        for (int i = 0; i < limit; i++) {
            Long id = Long.valueOf(i);

            product = new Product();
            product.setId(id);
            product.setName("Sergey Burlaka");
            product.setDescription("Description");

            productListFromNet.add(product);
        }
        return productListFromNet;
    }

    public static List<Product> getProductTestList(int offset, int limit) {
        List<Product> productListFromNet = new ArrayList<>();
        Product product;
        for (int i = 0; i < limit; i++) {
            Long id = Long.valueOf(i) + offset;
            product = new Product();
            product.setId(id);
            product.setName("Sergey Burlaka " + id);
            product.setDescription("Description " + id);
            productListFromNet.add(product);
        }
        return productListFromNet;
    }


    private static void rotateToLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private static void rotateToPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void rotateOrientation(Activity activity) {
        int currentOrientation = activity.getResources().getConfiguration().orientation;

        switch (currentOrientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                rotateToPortrait(activity);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                rotateToLandscape(activity);
                break;
            default:
                rotateToLandscape(activity);
        }
    }

    /**
     * Returns the content description for the navigation button view in the toolbar.
     */
    public static String getToolbarNavigationContentDescription(
            @NonNull Activity activity, @IdRes int toolbar1) {
        Toolbar toolbar = (Toolbar) activity.findViewById(toolbar1);
        if (toolbar != null) {
            return (String) toolbar.getNavigationContentDescription();
        } else {
            throw new RuntimeException("No toolbar found.");
        }
    }

    /**
     * Gets an Activity in the RESUMED stage.
     * <p>
     * This method should never be called from the Main thread. In certain situations there might
     * be more than one Activities in RESUMED stage, but only one is returned.
     * See {@link ActivityLifecycleMonitor}.
     */
    public static Activity getCurrentActivity() throws IllegalStateException {
        // The array is just to wrap the Activity and be able to access it from the Runnable.
        final Activity[] resumedActivity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    resumedActivity[0] = (Activity) resumedActivities.iterator().next();
                } else {
                    throw new IllegalStateException("No Activity in stage RESUMED");
                }
            }
        });
        return resumedActivity[0];
    }
}
