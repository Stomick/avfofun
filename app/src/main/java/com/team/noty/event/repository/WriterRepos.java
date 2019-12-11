package com.team.noty.event.repository;

import android.support.annotation.NonNull;

import io.reactivex.Completable;

public interface WriterRepos<New extends Identified>{

    Completable edit(@NonNull New new_);
}
