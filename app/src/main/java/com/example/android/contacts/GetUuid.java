package com.example.android.contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetUuid {
    private static final String URL =
            "https://run.mocky.io/v3/be5b5607-1aad-4e02-935f-d6df54abc102";

    static String getUuid() {
                String reg = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
                Matcher m = Pattern.compile(reg).matcher(URL);
                m.find();
                String uuid = m.group();
                System.out.println(uuid);
                return uuid;
            }
}
