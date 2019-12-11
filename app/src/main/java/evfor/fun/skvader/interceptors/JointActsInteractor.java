package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.exceptions.NoJointActsException;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class JointActsInteractor implements Interactor<Single<ActId>, String> {

    private ReaderRepos<User, Integer> userReader;

    @Inject
    JointActsInteractor(ReaderRepos<User, Integer> userReader) {
        this.userReader = userReader;
    }

    @Override
    public Single<ActId> call(String id) {
        return Single.zip(userReader.request(StringUtils.toIntOr0(AuthData.getID())),
                userReader.request(StringUtils.toIntOr0(id)), this::zip)
                .subscribeOn(Schedulers.io());
    }

    private ActId zip(User user0, User user1) throws NoJointActsException {
        if(user0.equals(user1))
            throw new NoJointActsException();
        return joinId(mergeIds(user0.communities, user0.events), mergeIds(user1.communities, user1.events));
    }

    private ActId joinId(List<ActId> oneUser, List<ActId> twoUser) throws NoJointActsException {
        ActId join = null;
        for (ActId id : oneUser)
            for (ActId id1 : twoUser)
                if (id.equals(id1)) {
                    join = id;
                    break;
                }
        if (join == null)
            throw new NoJointActsException();
        return join;
    }

    private List<ActId> mergeIds(List<String> comm, List<String> event) {
        if (comm == null)
            comm = new ArrayList<>();
        if (event == null)
            event = new ArrayList<>();
        List<ActId> list = createActIdList(comm, false);
        list.addAll(createActIdList(event, true));
        return list;
    }

    private List<ActId> createActIdList(List<String> ids, boolean isEvent) {
        List<ActId> actIds = new ArrayList<>();
        for (String id : ids)
            actIds.add(new ActId(id, isEvent));
        return actIds;
    }
}
