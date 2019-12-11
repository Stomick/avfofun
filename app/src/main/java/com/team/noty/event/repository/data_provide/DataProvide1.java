package com.team.noty.event.repository.data_provide;

import io.reactivex.Single;

public interface DataProvide1<Result, Parameter> {
    Single<Result> provide(Parameter parameter);
}
