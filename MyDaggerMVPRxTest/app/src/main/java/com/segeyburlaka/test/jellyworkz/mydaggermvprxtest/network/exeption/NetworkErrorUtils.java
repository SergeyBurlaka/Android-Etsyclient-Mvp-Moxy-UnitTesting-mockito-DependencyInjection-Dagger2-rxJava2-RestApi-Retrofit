package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption;


import android.util.Log;

import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.MyEtsyAppApplication;
import com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

import static com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util.JacksonUtil.createObjectMapper;

public final class NetworkErrorUtils {

    private static final String EXCEPTION_TAG = "NetworkErrorUtils";

    public static NetworkErrorUtils instance() {
        return Loader.sNetworkErrorUtils;
    }

    public <T> Func1<Throwable, Observable<T>> rxParseError() {
        return throwable -> Observable.error(parseError(throwable));
    }

    private Throwable parseError(Throwable e) {

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            return parseErrorResponseBody(httpException.response());
        } else if (e instanceof IOException) {
            return new NoNetworkException(MyEtsyAppApplication.getInstance().getString(R.string.connection_was_lost));
        } else {
            return new InternalAppException(e);
        }
    }

    private Exception parseErrorResponseBody(Response<?> response) {
        BufferedReader bufferedReader = null;
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(response.errorBody().byteStream());
            bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String newLine;
            while ((newLine = bufferedReader.readLine()) != null) {
                sb.append(newLine);
            }
            return createObjectMapper().readValue(sb.toString(), ApiError.class);

        } catch (IOException e) {
            Log.e(EXCEPTION_TAG, e.getMessage());
            return e;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(EXCEPTION_TAG, e.getMessage());
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(EXCEPTION_TAG, e.getMessage());
                }
            }
        }
    }

    private static final class Loader {
        private static final NetworkErrorUtils sNetworkErrorUtils = new NetworkErrorUtils();

        private Loader() throws IllegalAccessException {
            throw new IllegalAccessException("Loader class");
        }
    }
}
