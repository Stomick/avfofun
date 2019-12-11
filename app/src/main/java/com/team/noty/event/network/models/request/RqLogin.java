package com.team.noty.event.network.models.request;

import com.google.gson.annotations.SerializedName;

public class RqLogin {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public RqLogin() {
    }

    public RqLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
