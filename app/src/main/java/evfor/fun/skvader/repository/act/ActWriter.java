package evfor.fun.skvader.repository.act;

import android.support.annotation.NonNull;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Community;
import evfor.fun.skvader.models.Event;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.utils.DateFormatter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;

import static evfor.fun.skvader.utils.ListUtils.toStringWOBrackets;
import static evfor.fun.skvader.utils.RequestMapUtil.input;
import static evfor.fun.skvader.utils.RequestMapUtil.uploadFile;

public class ActWriter implements WriterRepos<Act> {
    private static final String EVENT = "evfor.fun.skvader", COMMUNITY = "community";

    private MainApi api;
    private ReaderRepos<User, Integer> userReader;
    private LocalRepository<User, Integer> userLocal;

    @Inject
    ActWriter(MainApi api, ReaderRepos<User, Integer> userReader, LocalRepository<User, Integer> userLocal) {
        this.api = api;
        this.userReader = userReader;
        this.userLocal = userLocal;
    }

    @Override
    public Completable edit(@NonNull Act new_) {
        String path = new_.isEvent ? EVENT : COMMUNITY;
        if(String.valueOf(new_.id).isEmpty())
            new_.id = null;
        return api.write(path, new_.id, createBody(new_), uploadFile(new_.imageUrl))
                .toCompletable()
                .andThen(userReader.request(AuthData.getIDInt()))
                .flatMapCompletable(new Update(new_));
    }

    private class Update implements Function<User, CompletableSource>{
        ActId actId;

        Update(ActId actId) {
            this.actId = actId;
        }

        @Override
        public CompletableSource apply(User user) throws Exception {
            user.addAct(actId);
            return userLocal.edit(user);
        }
    }

    private Map<String, RequestBody> createBody(Act act) {
        Map<String, RequestBody> requestBodyMap = act instanceof Event
                ? createBody((Event) act)
                : createBody((Community) act);
        input(requestBodyMap, "title", act.title);
        input(requestBodyMap, "category_id", act.category_id);
        input(requestBodyMap, "description", act.description);
        input(requestBodyMap, "place", act.address);
        input(requestBodyMap, "price", act.price);
        input(requestBodyMap, "age_limit", act.age_limit);
        input(requestBodyMap, "count_peoples", maxCount(act.max_count));
        input(requestBodyMap, "moderation",act.mod?"true":"false");
        input(requestBodyMap, "video", act.video);
        input(requestBodyMap, "latitude", act.latitude);
        input(requestBodyMap, "longitude", act.longitude);
        return requestBodyMap;
    }

    private Map<String, RequestBody> createBody(Event event) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        input(requestBodyMap, "date", DateFormatter.correctFormat(event.date));
        input(requestBodyMap, "time", event.time);
        return requestBodyMap;
    }

    private Map<String, RequestBody> createBody(Community comm) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        input(requestBodyMap, "visiting_days", toStringWOBrackets(comm.days));
        input(requestBodyMap, "start_time", comm.bTime);
        input(requestBodyMap, "end_time", comm.eTime);
        return requestBodyMap;
    }

    private int maxCount(int count) {
        int a = count == 0 || count > 9999 ? 9999 : count;
        return a;
    }
}
