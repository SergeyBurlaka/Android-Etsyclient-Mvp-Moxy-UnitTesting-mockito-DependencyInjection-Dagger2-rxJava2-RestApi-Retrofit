package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.preferences;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;

/**
 * Shared preferences utils
 */
public final class PrefProvider {

    private SharedPreferences mPreferences;

    private PrefProvider() {
        mPreferences = MyEtsyAppApplication.getInstance().getApplicationContext().getSharedPreferences(PreferencesContract.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static PrefProvider getInstance() {
        return Loader.sInstance;
    }

    /**
     * Save current count number of products results
     *
     * @param count - amount of searched product results
     */
    @SuppressLint("CommitPrefEdits")
    public void saveResultsCounter(Integer count) {
        mPreferences.edit()
                .putInt(PreferencesContract.Products.RESULT_COUNT, count)
                .commit();
    }


    /**
     * @return count - amount of searched product results
     */
    public Integer getResultsCounter() {
        return mPreferences.getInt(PreferencesContract.Products.RESULT_COUNT, 0);

    }

    private static final class Loader {
        private static final PrefProvider sInstance = new PrefProvider();

        private Loader() throws IllegalAccessException {
            throw new IllegalAccessException("Loader class");
        }
    }
}
