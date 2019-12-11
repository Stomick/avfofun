package evfor.fun.skvader.network.models.request;

import com.google.gson.annotations.SerializedName;

public class RqRegistration {
    @SerializedName("email")
    public String email;
    @SerializedName("surename")
    public String surename;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("phone")
    public String phone;
    @SerializedName("city")
    public String city;
    @SerializedName("gender")
    public String gender;
}
