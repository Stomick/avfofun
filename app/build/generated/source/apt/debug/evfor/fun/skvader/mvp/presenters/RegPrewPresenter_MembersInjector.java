// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.utils.PermissionController;
import java.util.List;
import javax.inject.Provider;

public final class RegPrewPresenter_MembersInjector implements MembersInjector<RegPrewPresenter> {
  private final Provider<WriterRepos<User>> userWriterProvider;

  private final Provider<ReaderRepos<User, Integer>> userReaderProvider;

  private final Provider<DataProvide<List<Category>>> categoryProvider;

  private final Provider<PermissionController> permissionControllerProvider;

  public RegPrewPresenter_MembersInjector(
      Provider<WriterRepos<User>> userWriterProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<DataProvide<List<Category>>> categoryProvider,
      Provider<PermissionController> permissionControllerProvider) {
    assert userWriterProvider != null;
    this.userWriterProvider = userWriterProvider;
    assert userReaderProvider != null;
    this.userReaderProvider = userReaderProvider;
    assert categoryProvider != null;
    this.categoryProvider = categoryProvider;
    assert permissionControllerProvider != null;
    this.permissionControllerProvider = permissionControllerProvider;
  }

  public static MembersInjector<RegPrewPresenter> create(
      Provider<WriterRepos<User>> userWriterProvider,
      Provider<ReaderRepos<User, Integer>> userReaderProvider,
      Provider<DataProvide<List<Category>>> categoryProvider,
      Provider<PermissionController> permissionControllerProvider) {
    return new RegPrewPresenter_MembersInjector(
        userWriterProvider, userReaderProvider, categoryProvider, permissionControllerProvider);
  }

  @Override
  public void injectMembers(RegPrewPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.userWriter = userWriterProvider.get();
    instance.userReader = userReaderProvider.get();
    instance.categoryProvider = categoryProvider.get();
    instance.permissionController = permissionControllerProvider.get();
  }

  public static void injectUserWriter(
      RegPrewPresenter instance, Provider<WriterRepos<User>> userWriterProvider) {
    instance.userWriter = userWriterProvider.get();
  }

  public static void injectUserReader(
      RegPrewPresenter instance, Provider<ReaderRepos<User, Integer>> userReaderProvider) {
    instance.userReader = userReaderProvider.get();
  }

  public static void injectCategoryProvider(
      RegPrewPresenter instance, Provider<DataProvide<List<Category>>> categoryProvider) {
    instance.categoryProvider = categoryProvider.get();
  }

  public static void injectPermissionController(
      RegPrewPresenter instance, Provider<PermissionController> permissionControllerProvider) {
    instance.permissionController = permissionControllerProvider.get();
  }
}