// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.dagger2.modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class RetrofitModule_ProvideRxJava2CallAdapterFactoryFactory
    implements Factory<RxJava2CallAdapterFactory> {
  private static final RetrofitModule_ProvideRxJava2CallAdapterFactoryFactory INSTANCE =
      new RetrofitModule_ProvideRxJava2CallAdapterFactoryFactory();

  @Override
  public RxJava2CallAdapterFactory get() {
    return Preconditions.checkNotNull(
        RetrofitModule.provideRxJava2CallAdapterFactory(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<RxJava2CallAdapterFactory> create() {
    return INSTANCE;
  }
}