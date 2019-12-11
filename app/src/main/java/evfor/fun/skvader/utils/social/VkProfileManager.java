package evfor.fun.skvader.utils.social;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

public class VkProfileManager implements FieldProfileTask {

    private MaybeEmitter<String> emitter;
    private String field;

    @Override
    public Maybe<String> execute(String field) {
        this.field = field;
        return Maybe.create(e -> {
            this.emitter = e;
            VKApi.users().get().executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    try {
                        emitter.onSuccess(getField(((VKList<VKApiUser>) response.parsedModel).get(0)));
                    } catch (ClassCastException ex) {
                        emitter.onError(ex);
                    }
                }

                @Override
                public void onError(VKError error) {
                    emitter.onError(new Exception(error.errorMessage));
                }
            });
        });
    }

    private String getField(VKApiUser user) {
        if (field.equalsIgnoreCase(FieldProfileTask.FNAME))
            return user.first_name;
        if (field.equalsIgnoreCase(FieldProfileTask.LNAME))
            return user.last_name;
        if (field.equalsIgnoreCase(FieldProfileTask.PICTURE))
            return user.photo_max_orig;
        if (field.equalsIgnoreCase(FieldProfileTask.EMAIL))
            return VKAccessToken.currentToken().email;

        return "";
    }
}
