package evfor.fun.skvader.repository.data_provide;

import evfor.fun.skvader.models.NotificationCount;
import evfor.fun.skvader.network.api.MainApi;

import javax.inject.Inject;

import io.reactivex.Single;

public class NotificationCountProvider implements DataProvide<NotificationCount> {

    private MainApi api;

    @Inject
    NotificationCountProvider(MainApi api) {
        this.api = api;
    }

    @Override
    public Single<NotificationCount> provide() {
        return api.getCountNoty()
                .map(rsCount -> rsCount.count)
                .map(NotificationCount::new);
    }
}
