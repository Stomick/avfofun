package com.team.noty.event.app;

import com.team.noty.event.models.User;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.StringUtils;

public class AuthData {
    public static String token;
    public static String id;
    public static String city = "Москва";

    public static boolean equalId(String userID) {
        return getID() != null && getID().equals(String.valueOf(userID));
    }

    public static boolean notEqualId(String userID) {
        return !equalId(userID);
    }

    public static boolean notEqualId(Integer userID) {
        return !equalId(String.valueOf(userID));
    }

    public static boolean notEqualId(User user) {
        return !equalId(user.id());
    }

    public static boolean equalId(Integer userID) {
        return equalId(String.valueOf(userID));
    }

    public static String fb_id;

    public static String getToken() {
        if (token == null)
            Injector.get().getApp().accountPreferenceManager().readInToData();
        return token;
    }

    public static String getID() {
        Injector.get().getApp().accountPreferenceManager().readInToData();
        return id;
    }
    public static Integer getIDInt() {
        Injector.get().getApp().accountPreferenceManager().readInToData();
        return StringUtils.toIntOr0(id);
    }

    public static boolean checkToken() {
        return Injector.get().getApp().accountPreferenceManager().checkValue(AccountPreferenceManager.TOKEN);
    }
}
