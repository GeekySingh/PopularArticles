package com.nagarro.nytimesarticle.network;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network manager to create network instance and handle network calls.
 */
public class NetworkManager {

    private static final int NETWORK_READ_TIMEOUT       = 10; //seconds
    private static final int NETWORK_CONNECTION_TIMEOUT = 10; //seconds

    private static NetworkManager mNetworkHelper;
    private static Retrofit mRetrofit;

    /**
     * Returns single instance of network manager to make network calls.
     * @param ctx context
     * @return network manager instance
     */
    public static synchronized NetworkManager getInstance(Context ctx) {
        if (mNetworkHelper == null) {
            mNetworkHelper = new NetworkManager(ctx);
        }
        return mNetworkHelper;
    }

    /**
     * Create and returns retrofit service for network calls.
     * @param tClass retrofit service class.
     * @return Retrofit service instance.
     */
    public <T> T createService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    /**
     * Private constructor
     * @param ctx context
     */
    private NetworkManager(Context ctx) {
        if (mRetrofit == null) {
            mRetrofit = getRetrofitInstance();
        }
    }

    /**
     * Returns instance of retrofit
     * @return retrofit instance
     */
    private static Retrofit getRetrofitInstance() {
        final int CACHE_SIZE = 10 * 1024 * 1024;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClient.connectTimeout(NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClient.cache(new Cache(Environment.getDataDirectory(), CACHE_SIZE));
        httpClient.interceptors().add(logging); // interceptor for logging retrofit data

        final Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().
                baseUrl(ApiEndPoint.BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(httpClient.build()).
                build();
    }
}
