package com.team.noty.event.services;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.team.noty.event.app.App;

public class TokenRefresh extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        if (App.getINSTANSE() != null)
            App.getINSTANSE().sendFBToken();
    }
}
