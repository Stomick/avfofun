package evfor.fun.skvader.utils.social;

import android.app.Activity;
import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

public class VKLoginManager implements SocialLogin {

    private String[] scope = new String[]{VKScope.EMAIL};

    private Activity activity;
    private VkLoginCallBack callBack;

    public VKLoginManager(Activity activity) {
        SocialProfileManager.choseSocial(Social.VK);
        this.activity = activity;
    }

    @Override
    public Maybe<String> requestId() {
        return Maybe.create(e -> {
            VKSdk.login(activity, scope);
            callBack = new VkLoginCallBack(e);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKSdk.onActivityResult(requestCode, resultCode, data, callBack);
    }

    private class VkLoginCallBack implements VKCallback<VKAccessToken> {
        MaybeEmitter<String> emitter;

        VkLoginCallBack(MaybeEmitter<String> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onResult(VKAccessToken res) {
            if (emitter != null && !emitter.isDisposed()) {
                if (res.userId == null) {
                    emitter.onError(new Exception("user=null"));
                    return;
                }
                emitter.onSuccess(res.userId);
            }
        }

        @Override
        public void onError(VKError error) {
            if (emitter != null && !emitter.isDisposed())
                if (error.httpError != null)
                    emitter.onError(error.httpError);

        }
    }

    @Override
    public void onDestroy() {
        callBack = null;
        activity = null;
    }
}
