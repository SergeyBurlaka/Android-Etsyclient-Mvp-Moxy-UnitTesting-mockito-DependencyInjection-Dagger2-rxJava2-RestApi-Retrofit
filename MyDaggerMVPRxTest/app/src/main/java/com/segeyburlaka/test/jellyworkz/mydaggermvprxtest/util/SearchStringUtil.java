package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;


import java.util.List;

public class SearchStringUtil {
    private SearchStringUtil() {
        //no instance
    }

    public static String getSearchString(List<String> keyWords) {
        StringBuilder keyStrBuild = new StringBuilder();
        int i = 0;
        for (String keyWord : keyWords) {
            if (!keyWord.isEmpty()) {
                keyStrBuild.append(keyWord);
            }
            if (i++ < keyWords.size() - 1) {
                keyStrBuild.append(" ");
            }
        }
        return keyStrBuild.toString();
    }
}
