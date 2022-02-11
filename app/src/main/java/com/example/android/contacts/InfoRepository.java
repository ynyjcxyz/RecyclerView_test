package com.example.android.contacts;

import static com.example.android.contacts.GetService.service;
import java.util.List;
import io.reactivex.Single;

public class InfoRepository {
    public static Single<List<Info>> getDto(String uuid) {
        return service().getAllContacts(uuid);
    }
}
