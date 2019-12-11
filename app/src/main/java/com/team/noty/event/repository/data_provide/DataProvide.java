package com.team.noty.event.repository.data_provide;

import io.reactivex.Single;

public interface DataProvide<What> {
    Single<What> provide();
}
