package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.preferences;

/**
 * Shared preferences contract.
 */
public final class PreferencesContract {

    static final String PREFS_NAME = "MissKidsApp";

    private PreferencesContract() {
        //no instance
    }

    static final class Products {

        public static final String RESULT_COUNT = "resultsCount";

        private Products(){
           //no instance
       }
    }
}
