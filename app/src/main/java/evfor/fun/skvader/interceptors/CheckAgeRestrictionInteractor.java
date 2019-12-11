package evfor.fun.skvader.interceptors;

import android.util.Log;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.exceptions.AgeLimitException;
import evfor.fun.skvader.models.AgeRestriction;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.ReaderRepos;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CheckAgeRestrictionInteractor implements Interactor<Completable, AgeRestriction> {

    private ReaderRepos<User, Integer> userReader;
    private Interactor<Integer, Calendar> ageCalculating;

    @Inject
    CheckAgeRestrictionInteractor(ReaderRepos<User, Integer> userReader, Interactor<Integer, Calendar> ageCalculating) {
        this.userReader = userReader;
        this.ageCalculating = ageCalculating;
    }

    @Override
    public Completable call(AgeRestriction ageRestriction) {
        return userReader.request(AuthData.getIDInt())
                .observeOn(AndroidSchedulers.mainThread())
                .map(User::getBirthday)
                .map(ageCalculating::call)
                .zipWith(Single.just(ageRestriction).map(AgeRestriction::getAge)
                        .doOnError(thr->Log.e("my",thr.getMessage())),
                        this::restrictionDifference)
                .doOnError(mes->Log.e("my",mes.getMessage()))
                .toCompletable();
}

    private int restrictionDifference(Integer age, Integer restriction) throws AgeLimitException {
        int diff = age - restriction;
        if (diff < 0) throw new AgeLimitException(Math.abs(diff));
        else return diff;
    }
}
