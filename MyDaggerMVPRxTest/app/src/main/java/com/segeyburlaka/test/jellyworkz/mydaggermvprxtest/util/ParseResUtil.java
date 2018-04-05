package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;


import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;

import java.util.HashMap;
import java.util.Map;

public class ParseResUtil {

    private ParseResUtil() {
    }

    public static Map<String, String> parseStringArray(int stringArrayResourceId) {

        String[] stringArray = MyEtsyAppApplication.getInstance().getResources().getStringArray(stringArrayResourceId);
        Map<String, String> outputArray = new HashMap<>();
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|", 2);
            outputArray.put(splitResult[0], splitResult[1]);
        }
        return outputArray;
    }
}
