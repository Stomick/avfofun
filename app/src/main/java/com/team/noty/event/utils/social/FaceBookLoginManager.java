package com.team.noty.event.utils.social;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.team.noty.event.R;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.ui.utils.ActivityReceiver;

import javax.inject.Inject;

import butterknife.OnClick;
import io.paperdb.Paper;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

import static com.team.noty.event.app.AuthData.fb_id;


public class FaceBookLoginManager implements SocialLogin {
    private LoginButton button;
    private CallbackManager callbackManager;
    private MaybeEmitter<String> emitter;

    public FaceBookLoginManager(LoginButton button) {
        SocialProfileManager.choseSocial(Social.FACEBOOK);
        this.button = button;
        callbackManager = CallbackManager.Factory.create();
        this.button.setReadPermissions(FacebookProfileTask.permissions());
    }

    public Maybe<String> requestId() {
        return Maybe.create(e -> {
            if (this.emitter == null || this.emitter.isDisposed())
                this.emitter = e;
            setCallbackManager();
        });
    }

    private void setCallbackManager() {
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (emitter != null && !emitter.isDisposed()) {
                    emitter.onSuccess(loginResult.getAccessToken().getUserId());
                    fb_id = loginResult.getAccessToken().getUserId();
                }
            }

            @Override
            public void onCancel() {
                if (emitter != null && !emitter.isDisposed())
                    emitter.onError(new Exception(button.getContext().getString(R.string.cancel)));
            }

            @Override
            public void onError(FacebookException e) {
                if (emitter != null && !emitter.isDisposed())
                    emitter.onError(e);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        button = null;
        emitter = null;
    }
}
