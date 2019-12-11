package com.team.noty.event.interceptors;

import com.team.noty.event.models.NewPassword;
import com.team.noty.event.network.api.MainApi;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import okhttp3.RequestBody;

import static com.team.noty.event.utils.RequestMapUtil.input;

public class EditPasswordInteractor implements Interactor<Completable, NewPassword> {

    private MainApi api;

    @Inject
    public EditPasswordInteractor(MainApi api) {
        this.api = api;
    }

    @Override
    public Completable call(NewPassword newPassword) {
        return api.mainEditProfile(newPass(newPassword.old, newPassword.new_))
                .toCompletable();
    }

    private Map<String, RequestBody> newPass(String pass, String newPass) {
        Map<String, RequestBody> map = new HashMap<>();
        input(map, "password", pass);
        input(map, "newpassword", newPass);
        return map;
    }
}
