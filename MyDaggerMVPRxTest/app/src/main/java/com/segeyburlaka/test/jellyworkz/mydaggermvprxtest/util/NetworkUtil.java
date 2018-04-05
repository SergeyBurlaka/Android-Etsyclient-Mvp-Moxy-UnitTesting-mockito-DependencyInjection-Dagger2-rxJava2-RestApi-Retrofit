package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;


import okhttp3.Credentials;

public class NetworkUtil {

    private NetworkUtil() {
        //no instance
    }

    public static String getCredentials(String user, String password) {
        return Credentials.basic(user, password);
    }
}
