package evfor.fun.skvader.utils.social;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SocialProfileManager {

    private static Social current;

    public static Social social() {
        return current;
    }

    public static void choseSocial(Social social) {
        current = social;

    }

    private static FieldProfileTask create() {
        if (current == null)
            return FieldProfileTask.empty;
        switch (current) {
            case VK:
                return new VkProfileManager();
            case FACEBOOK:
                return new FacebookProfileTask();
            default:
                return FieldProfileTask.empty;
        }
    }

    public static Maybe<String> request(String fieldName) {
        return create()
                .execute(fieldName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .onErrorComplete();
    }
}
