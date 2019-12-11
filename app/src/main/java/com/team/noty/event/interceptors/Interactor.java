package com.team.noty.event.interceptors;

import io.reactivex.disposables.Disposable;

public interface Interactor<Out, In> {
    Out call(In in);
}
