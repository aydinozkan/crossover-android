package com.aydozkan.crossover.network.http;

import com.aydozkan.crossover.utility.SharedPreferenceWrapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Custom Interceptor class to inject AccessToken into Header
 */
class HeaderInterceptor implements Interceptor {

    HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder()
                .header("Authorization", SharedPreferenceWrapper.getInstance().getAccessToken())
                .build();

        return chain.proceed(newRequest);
    }
}
