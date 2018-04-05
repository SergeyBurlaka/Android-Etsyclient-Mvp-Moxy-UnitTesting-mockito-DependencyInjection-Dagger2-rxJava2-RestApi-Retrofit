package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    private TextUtil() {
        //no instance
    }

    public static String removeStartingIndex(String text) {
        if (text == null) {
            return "";
        }
        return text.replaceAll(MyEtsyAppApplication.getInstance().getString(R.string.starting_index_pattern), "");
    }

    public static String getStartingIndex(String text) {
        if (text == null) {
            return "";
        }
        Pattern p = Pattern.compile(MyEtsyAppApplication.getInstance().getString(R.string.starting_index_pattern));
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(0);
        } else return "";
    }
}

