package com.example.android.contacts;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class OkhttpClientUtil {

    static OkHttpClient buildOkHttpClient(){
        return new OkHttpClient
                .Builder()
                .addInterceptor
                        (new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }
}