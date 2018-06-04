# 简介

### 该项目是根据鸿洋的wanAndroid开发的一款个人App,一个致力于分享各种Android干货的平台。如果您有任何好的建议，可以在[https://github.com/hongyangAndroid/xueandroid](https://github.com/hongyangAndroid/xueandroid)项目中以issu的形式提出好的建议。

# Apk

 <a href="https://www.pgyer.com/6osT" target="_blank">1.0.1</a>

# 接口

  <a href="http://www.wanandroid.com/blog/show/2" target="_blank">玩Android</a>
  
# 技术框架( Material Design + RxJava2 + Retrofit + Dagger2  + Glide)
  -  <a href="https://github.com/amitshekhariitbhu/RxJava2-Android-Samples" target="_blank">RxJava2</a>
  - <a href="http://square.github.io/retrofit/" target="_blank">Retrofit</a>
  -  <a href="https://github.com/google/dagger" target="_blank">Dagger</a>
  -  <a href="https://github.com/bumptech/glide" target="_blank">Glide</a>
  -  <a href="https://github.com/Justson/AgentWeb" target="_blank">AgentWeb</a>
  
# 版本迭代
### V1.0.1
  -  首页
  -  知识体系
  -  登录、注册
  -  收藏
  -  搜索
  -  分享
  
# 功能
  
## 登录和注册
  -  登录
  -  注册

## 首页相关
  -  首页文章列表
  -  首页banner


## 体系
  -  知识体系
  -  知识体系下的文章

## 收藏
 -   收藏文章列表
 -   收藏站内文章
 -   取消收藏(文章列表和我的收藏页面)
 
## 部分截图


## 下载APK（Android5.0或以上）

![](https://i.imgur.com/VAyufMy.png)
 
## 技术点
 -  项目代码尽力遵循了阿里巴巴Java开发规范和阿里巴巴Android开发规范，并有良好的注释。
 -  使用RxJava2结合Retrofit2进行网络请求。
 -  使用RxJava2的操作符对事件流进行进行转换、延时、过滤等操作，其中使用Compose操作符结合RxUtils工具类简化线程切换调用的代码数量。
 -  使用Dagger2无耦合地将Model注入Presenter、Presenter注入View，更高效地实现了MVP模式。
 -  使用BasePresenter对事件流订阅的生命周期做了集成管理。
 -  使用Material Design中的Behavior集合ToolBar实现了响应式的“上失下现”特效。
 -  多处使用了滑动到顶部的悬浮按钮，提升阅读的便利性。
 -  使用SmartRefreshLayout丰富的刷新动画将项目的美提升了一个档次。
 -  项目中多处使用了炫目的动画及特效。
 -  更多请Clone本项目进行查看。。。
 
 
  
# License

 Copyright 2018 JsonChao
 
 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.