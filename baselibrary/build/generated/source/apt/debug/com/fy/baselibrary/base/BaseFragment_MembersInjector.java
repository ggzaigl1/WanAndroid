package com.fy.baselibrary.base;

import com.fy.baselibrary.retrofit.ApiService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseFragment_MembersInjector implements MembersInjector<BaseFragment> {
  private final Provider<ApiService> mConnServiceProvider;

  public BaseFragment_MembersInjector(Provider<ApiService> mConnServiceProvider) {
    assert mConnServiceProvider != null;
    this.mConnServiceProvider = mConnServiceProvider;
  }

  public static MembersInjector<BaseFragment> create(Provider<ApiService> mConnServiceProvider) {
    return new BaseFragment_MembersInjector(mConnServiceProvider);
  }

  @Override
  public void injectMembers(BaseFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mConnService = mConnServiceProvider.get();
  }

  public static void injectMConnService(
      BaseFragment instance, Provider<ApiService> mConnServiceProvider) {
    instance.mConnService = mConnServiceProvider.get();
  }
}
