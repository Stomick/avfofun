package com.team.noty.event.utils;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestMapUtil {
    private static final String imageFileName = "imageFile";

    public static void input(Map<String, RequestBody> map, String key, Object value) {
        if (value != null && !String.valueOf(value).isEmpty())
            map.put(key, create(String.valueOf(value)));
    }

    public static RequestBody create(String string) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, string);
    }

    public static MultipartBody.Part uploadFile(String path) {
        if (path == null) return null;
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(imageFileName, file.getName(), requestFile);
    }
}
