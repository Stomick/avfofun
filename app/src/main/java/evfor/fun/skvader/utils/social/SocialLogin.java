package evfor.fun.skvader.utils.social;

import evfor.fun.skvader.ui.utils.ActivityReceiver;

import io.reactivex.Maybe;

public interface SocialLogin extends ActivityReceiver {
    Maybe<String> requestId();
    void onDestroy();
}
