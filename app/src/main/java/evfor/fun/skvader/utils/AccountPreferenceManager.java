package evfor.fun.skvader.utils;

import android.content.SharedPreferences;

import evfor.fun.skvader.app.AuthData;

public class AccountPreferenceManager {
    public static final String TAG = AccountPreferenceManager.class.getSimpleName();
    private SharedPreferences preferences;
    public static final String TOKEN = "TOKEN";
    public static final String ID = "id";

    public AccountPreferenceManager(SharedPreferences preferences) {
        this.preferences = preferences;
        readInToData();
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    public void saveFromData() {
        preferences.edit()
                .putString(TOKEN, AuthData.token)
                .putString(ID, AuthData.id)
                .apply();
    }

    public boolean checkValue(String key) {
        return preferences.contains(key);
    }

    public void readInToData() {
        AuthData.token = preferences.getString(TOKEN, null);
        AuthData.id = preferences.getString(ID, null);
    }
}