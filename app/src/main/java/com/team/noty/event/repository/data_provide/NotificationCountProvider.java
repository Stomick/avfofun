package com.team.noty.event.repository.data_provide;

import com.team.noty.event.models.NotificationCount;
import com.team.noty.event.network.api.MainApi;

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
