package com.team.noty.event.models;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfile {
    public MultipartBody.Part image;
    public Map<String, RequestBody> body;
}
