package com.fy.baselibrary.base;

import com.fy.baselibrary.retrofit.ApiService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseActivity_MembersInjector implements MembersInjector<BaseActivity> {
  private final Provider<ApiService> mConnServiceProvider;

  public BaseActivity_MembersInjector(Provider<ApiService> mConnServiceProvider) {
    assert mConnServiceProvider != null;
    this.mConnServiceProvider = mConnServiceProvider;
  }

  public static MembersInjector<BaseActivity> create(Provider<ApiService> mConnServiceProvider) {
    return new BaseActivity_MembersInjector(mConnServiceProvider);
  }

  @Override
  public void injectMembers(BaseActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mConnService = mConnServiceProvider.get();
  }

  public static void injectMConnService(
      BaseActivity instance, Provider<ApiService> mConnServiceProvider) {
    instance.mConnService = mConnServiceProvider.get();
  }
}
