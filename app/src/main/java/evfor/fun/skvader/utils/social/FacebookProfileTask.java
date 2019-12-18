package evfor.fun.skvader.utils.social;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import evfor.fun.skvader.utils.Log;

import org.json.JSONException;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

public class FacebookProfileTask implements FieldProfileTask {

    public static String[] permissions() {
        return new String[]{
                EMAIL,/*
                "user_" + BDAY,
                "user_" + GENDER,
                "user_" + HTOWN,
                "user_" + LOCATION*/
        };
    }

    private MaybeEmitter<String> emitter;
    private String field;

    @Override
    public Maybe<String> execute(String field) {
        this.field = field;
        return Maybe.create(e -> {
            emitter = e;
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    (object, response) -> {
                        if (this.field != null) {
                            Log.d(getClass(), String.valueOf(object));
                            try {
                                emitter.onSuccess(object.getString(this.field));
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString(GraphRequest.FIELDS_PARAM, this.field);
            request.setParameters(parameters);
            request.executeAndWait();
        });
    }
}
