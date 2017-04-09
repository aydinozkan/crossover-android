package com.aydozkan.crossover.network.http;

import com.aydozkan.crossover.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton Client implementation for Square's Retrofit.
 */
public final class RetrofitClient {

    //Timeout values in TimeUnit.SECONDS
    private static final long DEFAULT_CONNECT_TIMEOUT = 20;
    private static final long DEFAULT_READ_TIMEOUT = 20;
    private static final long DEFAULT_WRITE_TIMEOUT = 20;

    private static final RetrofitClient OUR_INSTANCE = new RetrofitClient();
    private final Retrofit mRetrofit;

    /**
     * Retrofit Connection Builder
     */
    private RetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    /**
     * Returns Our RetrofitClient instance
     *
     * @return Our RetrofitClient instance
     */
    public static RetrofitClient getInstance() {
        return OUR_INSTANCE;
    }

    /**
     * Returns Retrofit instance
     *
     * @return Retrofit instance
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
