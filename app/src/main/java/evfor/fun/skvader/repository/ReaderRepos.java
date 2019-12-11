package evfor.fun.skvader.repository;

import android.support.annotation.NonNull;

import io.reactivex.Single;

public interface ReaderRepos<Out, In> {
    @NonNull
    Single<Out> request(@NonNull In in);
}
