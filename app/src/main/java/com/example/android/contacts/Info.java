package com.example.android.contacts;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@GenerateTypeAdapter
@AutoValue

public abstract class Info {
    @Nullable
    @SerializedName("name")
    abstract String name();

    @Nullable
    @SerializedName("image")
    abstract String image();

    @Nullable
    @SerializedName("phone")
    abstract String phone();

    public static Info create(String name , String image , String phone) {
        return new AutoValue_Info(name, image, phone);
    }
}