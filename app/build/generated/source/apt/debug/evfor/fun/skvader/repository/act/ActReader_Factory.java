// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.repository.act;

import dagger.internal.Factory;
import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.repository.cache.Cache;
import io.reactivex.Single;
import javax.inject.Provider;

public final class ActReader_Factory implements Factory<ActReader> {
  private final Provider<MainApi> apiProvider;

  private final Provider<Interactor<Single<Category>, String>> categoryByIdProvider;

  private final Provider<Converter<Act, RsAct>> converterProvider;

  private final Provider<Cache<ActId, Act>> cacheProvider;

  public ActReader_Factory(
      Provider<MainApi> apiProvider,
      Provider<Interactor<Single<Category>, String>> categoryByIdProvider,
      Provider<Converter<Act, RsAct>> converterProvider,
      Provider<Cache<ActId, Act>> cacheProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert categoryByIdProvider != null;
    this.categoryByIdProvider = categoryByIdProvider;
    assert converterProvider != null;
    this.converterProvider = converterProvider;
    assert cacheProvider != null;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public ActReader get() {
    return new ActReader(
        apiProvider.get(),
        categoryByIdProvider.get(),
        converterProvider.get(),
        cacheProvider.get());
  }

  public static Factory<ActReader> create(
      Provider<MainApi> apiProvider,
      Provider<Interactor<Single<Category>, String>> categoryByIdProvider,
      Provider<Converter<Act, RsAct>> converterProvider,
      Provider<Cache<ActId, Act>> cacheProvider) {
    return new ActReader_Factory(
        apiProvider, categoryByIdProvider, converterProvider, cacheProvider);
  }

  /** Proxies {@link ActReader#ActReader(MainApi, Interactor, Converter, Cache)}. */
  public static ActReader newActReader(
      MainApi api,
      Interactor<Single<Category>, String> categoryById,
      Converter<Act, RsAct> converter,
      Cache<ActId, Act> cache) {
    return new ActReader(api, categoryById, converter, cache);
  }
}