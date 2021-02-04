package com.ripple.util;

import com.google.gson.Gson;

public class GsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Object fromJson(String object, Class clz) {
        return gson.fromJson(object, clz.getClass());
    }
}
