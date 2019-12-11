package com.team.noty.event.network.models.request;

public class RqChangePassword {
    public String oldpassword;
    public String newpassword;

    public RqChangePassword(String oldpassword, String newpassword) {
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }
}
