package com.ggz.baselibrary.retrofit;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作为桥梁，沟通调用者和依赖对象库
 *
 * @author fangs
 * @date 2017/5/15
 */
@Singleton
@Component(modules = RequestModule.class)
public interface RequestComponent {

    void inJect(RequestUtils requestConn);
}
