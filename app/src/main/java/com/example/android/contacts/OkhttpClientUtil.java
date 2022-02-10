package com.example.android.contacts;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class OkhttpClientUtil {
    private static Interceptor logger() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    static OkHttpClient buildOkHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(logger()).build();
    }
}
