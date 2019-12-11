package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.FullAct;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class FullActInterceptor implements Interactor<Single<FullAct>, ActId> {

    private ReaderRepos<Act, ActId> actReader;
    private ReaderRepos<User, Integer> userReader;

    @Inject
    FullActInterceptor(ReaderRepos<Act, ActId> actReader,
                              ReaderRepos<User, Integer> userReader) {
        this.actReader = actReader;
        this.userReader = userReader;
    }

    @Override
    public Single<FullAct> call(ActId actId) {
        return actReader.request(actId)
                .flatMap(act ->
                        Single.zip(Single.just(act),
                                userReader.request(StringUtils.toIntOr0(act.user_id)),
                                userList(act.users),
                                FullAct::new));
    }

    private Single<List<User>> userList(List<String> ids) {
        return Observable.fromIterable(ids)
                .map(StringUtils::toIntOr0)
                .flatMapSingle(userReader::request)
                .toList();
    }

}
