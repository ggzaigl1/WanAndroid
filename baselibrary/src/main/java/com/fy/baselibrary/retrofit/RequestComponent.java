package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作为桥梁，沟通调用者和依赖对象库
 * Created by fangs on 2017/5/15.
 */
@Singleton
@Component(modules = RequestModule.class)
public interface RequestComponent {

    void inJect(BaseActivity requestConn);
    void inJect(BaseFragment requestConn);
}
