package com.example.android.contacts;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {
    @GET("v3/{uuid}")
    Single<List<Info>> getAllContacts(@Path("uuid") String parameterUuid);
}