// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.repository.act;

import dagger.internal.Factory;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.ReaderRepos;
import javax.inject.Provider;

public final class ActWriter_Factory implements Factory<ActWriter> {
  private final Provider<MainApi> apiProvider;

  private final Provider<ReaderRepos<User, Integer>> userReaderProvider;

  private final Provider<LocalRepository<User, Integer>> userLocalProvider;

  public ActWriter_Factory(
      Provider<MainApi> apiProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<LocalRepository<User, Integer>> userLocalProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert userReaderProvider != null;
    this.userReaderProvider = userReaderProvider;
    assert userLocalProvider != null;
    this.userLocalProvider = userLocalProvider;
  }

  @Override
  public ActWriter get() {
    return new ActWriter(apiProvider.get(), userReaderProvider.get(), userLocalProvider.get());
  }

  public static Factory<ActWriter> create(
      Provider<MainApi> apiProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<LocalRepository<User, Integer>> userLocalProvider) {
    return new ActWriter_Factory(apiProvider, userReaderProvider, userLocalProvider);
  }

  /** Proxies {@link ActWriter#ActWriter(MainApi, ReaderRepos, LocalRepository)}. */
  public static ActWriter newActWriter(
      MainApi api,
      ReaderRepos<User, Integer> userReader,
      LocalRepository<User, Integer> userLocal) {
    return new ActWriter(api, userReader, userLocal);
  }
}