// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.repository.user;

import dagger.internal.Factory;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.cache.Cache;
import javax.inject.Provider;

public final class UserReader_Factory implements Factory<UserReader> {
  private final Provider<MainApi> apiProvider;

  private final Provider<LocalRepository<User, Integer>> localRepositoryProvider;

  private final Provider<Cache<Integer, User>> cacheProvider;

  public UserReader_Factory(
      Provider<MainApi> apiProvider,
      Provider<LocalRepository<User, Integer>> localRepositoryProvider,
      Provider<Cache<Integer, User>> cacheProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert localRepositoryProvider != null;
    this.localRepositoryProvider = localRepositoryProvider;
    assert cacheProvider != null;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public UserReader get() {
    return new UserReader(apiProvider.get(), localRepositoryProvider.get(), cacheProvider.get());
  }

  public static Factory<UserReader> create(
      Provider<MainApi> apiProvider,
      Provider<LocalRepository<User, Integer>> localRepositoryProvider,
      Provider<Cache<Integer, User>> cacheProvider) {
    return new UserReader_Factory(apiProvider, localRepositoryProvider, cacheProvider);
  }
}