package com.example.android.contacts;

import static com.example.android.contacts.GsonUtil.getGson;
import static com.example.android.contacts.OkhttpClientUtil.buildOkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetService {
    private static final String BASE_URL = "https://run.mocky.io/";

    static DataService service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
/**                A call adapter which uses RxJava 2 for creating observables.
                 Adding this class to Retrofit allows you to return an
                 Observable, Flowable, Single, Completable or Maybe from service methods.
                 Without it you cant describe interface as rx type*/
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(buildOkHttpClient())
                .build();
        return retrofit.create(DataService.class);
    }
}