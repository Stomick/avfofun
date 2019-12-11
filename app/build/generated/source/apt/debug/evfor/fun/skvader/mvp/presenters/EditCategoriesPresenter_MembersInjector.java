// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import com.pacoworks.rxpaper2.RxPaperBook;
import dagger.MembersInjector;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.Updater;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import java.util.List;
import javax.inject.Provider;

public final class EditCategoriesPresenter_MembersInjector
    implements MembersInjector<EditCategoriesPresenter> {
  private final Provider<Updater<User, Integer>> updaterProvider;

  private final Provider<ReaderRepos<User, Integer>> userReaderProvider;

  private final Provider<DataProvide<List<Category>>> categoriesProvider;

  private final Provider<MainApi> apiProvider;

  private final Provider<ContentApi> contentApiProvider;

  private final Provider<RxPaperBook> bookProvider;

  public EditCategoriesPresenter_MembersInjector(
      Provider<Updater<User, Integer>> updaterProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<DataProvide<List<Category>>> categoriesProvider,
      Provider<MainApi> apiProvider,
      Provider<ContentApi> contentApiProvider,
      Provider<RxPaperBook> bookProvider) {
    assert updaterProvider != null;
    this.updaterProvider = updaterProvider;
    assert userReaderProvider != null;
    this.userReaderProvider = userReaderProvider;
    assert categoriesProvider != null;
    this.categoriesProvider = categoriesProvider;
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert contentApiProvider != null;
    this.contentApiProvider = contentApiProvider;
    assert bookProvider != null;
    this.bookProvider = bookProvider;
  }

  public static MembersInjector<EditCategoriesPresenter> create(
      Provider<Updater<User, Integer>> updaterProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<DataProvide<List<Category>>> categoriesProvider,
      Provider<MainApi> apiProvider,
      Provider<ContentApi> contentApiProvider,
      Provider<RxPaperBook> bookProvider) {
    return new EditCategoriesPresenter_MembersInjector(
        updaterProvider,
        userReaderProvider,
        categoriesProvider,
        apiProvider,
        contentApiProvider,
        bookProvider);
  }

  @Override
  public void injectMembers(EditCategoriesPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.updater = updaterProvider.get();
    instance.userReader = userReaderProvider.get();
    instance.categoriesProvider = categoriesProvider.get();
    instance.api = apiProvider.get();
    instance.contentApi = contentApiProvider.get();
    instance.book = bookProvider.get();
  }

  public static void injectUpdater(
      EditCategoriesPresenter instance, Provider<Updater<User, Integer>> updaterProvider) {
    instance.updater = updaterProvider.get();
  }

  public static void injectUserReader(
      EditCategoriesPresenter instance, Provider<ReaderRepos<User, Integer>> userReaderProvider) {
    instance.userReader = userReaderProvider.get();
  }

  public static void injectCategoriesProvider(
      EditCategoriesPresenter instance, Provider<DataProvide<List<Category>>> categoriesProvider) {
    instance.categoriesProvider = categoriesProvider.get();
  }

  public static void injectApi(EditCategoriesPresenter instance, Provider<MainApi> apiProvider) {
    instance.api = apiProvider.get();
  }

  public static void injectContentApi(
      EditCategoriesPresenter instance, Provider<ContentApi> contentApiProvider) {
    instance.contentApi = contentApiProvider.get();
  }

  public static void injectBook(
      EditCategoriesPresenter instance, Provider<RxPaperBook> bookProvider) {
    instance.book = bookProvider.get();
  }
}