package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyUtils {

    private static final Map<String, String> MY_CURRENCIES = new HashMap<String, String>() {
        {
            put(MyEtsyAppApplication.getInstance().getString(R.string.EURO), MyEtsyAppApplication.getInstance().getString(R.string.euro_symbol));
            put(MyEtsyAppApplication.getInstance().getString(R.string.USD), MyEtsyAppApplication.getInstance().getString(R.string.dollar_symbol));
        }
    };

    private CurrencyUtils() {
        //no instance
    }

    public static String getPrice(Double price, String currencyCode) {

        Currency currency = Currency.getInstance(currencyCode);

        String symbol = MY_CURRENCIES.get(currency.getCurrencyCode());
        if (symbol == null) {
            symbol = currency.getSymbol();
        }
        if (symbol != null) {
            return String.format(Locale.getDefault(), "%s%,.2f", symbol, price);
        } else {
            return String.valueOf(price);
        }
    }
}