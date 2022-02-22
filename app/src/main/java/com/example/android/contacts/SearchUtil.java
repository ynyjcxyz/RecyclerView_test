package com.example.android.contacts;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchUtil {
    @RequiresApi(api = Build.VERSION_CODES.N)
    static List<Info> getFilterList(String filter, List<Info>contactList){

        return contactList
                .stream()
                .filter(person-> Objects.requireNonNull(person.name()).toLowerCase().contains(filter.toLowerCase()) || (Objects.requireNonNull(person.phone())).contains(filter))
                .collect(Collectors.toList());
    }
}