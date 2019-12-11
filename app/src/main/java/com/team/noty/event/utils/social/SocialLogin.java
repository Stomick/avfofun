package com.team.noty.event.utils.social;

import com.team.noty.event.ui.utils.ActivityReceiver;

import io.reactivex.Maybe;

public interface SocialLogin extends ActivityReceiver {
    Maybe<String> requestId();
    void onDestroy();
}
