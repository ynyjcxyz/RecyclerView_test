package com.example.android.contacts;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {
    @GET("v3/{uuid}")
    Call<List<Info>> getAllContacts(@Path("uuid") String parameterUuid);
}