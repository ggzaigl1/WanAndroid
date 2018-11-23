package com.example.gab.babylove.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.application.BaseApplication;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.cache.ACache;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 *
 * @author fangs
 * @date 2017/4/26
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "Fragment";

    protected ACache mCache;
    protected KProgressHUD mKProgressHUD;
    protected AppCompatActivity mContext;
    protected View mRootView;
    protected Unbinder unbinder;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(setContentLayout(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);
            if (NetworkUtils.isNetworkAvailable(ConfigUtils.getAppCtx())) {
                baseInit();
            } else {
                T.showShort("好像没有网络耶~");
            }
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        LogUtils.e(TAG, "onCreateView()");
        return mRootView;
    }

    @Override
    public void onClick(View view) {
    }

    protected void baseInit() {
    }

    protected abstract int setContentLayout();


    /**
     * Fragment生命周期管理
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 不在最前端界面显示
        if (hidden) {
            onPause();
            // 重新显示到最前端中
        } else {
            onResume();
        }
    }

    /**
     * 收藏
     * @param view
     * @param id
     */
    protected void getCollectArticle(View view, int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        mKProgressHUD.dismiss();
                        Snackbar.make(view, R.string.collection_success, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    /**
     * 取消收藏
     *
     * @param id
     */
    protected void getUnCollectArticle(int id) {
        mKProgressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .unCollectArticle(id, "")
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<Object>() {
                    @Override
                    protected void onSuccess(Object t) {
                        T.showShort(getString(R.string.cancel_collection_success));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @Override//当Activity中的onCreate方法执行完后调用
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e(TAG, "onActivityCreated()");
    }

    @Override//Fragment和Activity建立关联的时候调用
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e(TAG, "onAttach()");

        this.mContext = (AppCompatActivity) context;
        mCache = ACache.get(mContext);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e(TAG, "onCreate()");
    }


    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e(TAG, "onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.e(TAG, "onStop()");
    }

    @Override//Fragment中的布局被移除时调用
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy()");

        if (null != unbinder) {
            unbinder.unbind();
        }

        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }

        RefWatcher refWatcher = BaseApplication.getRefWatcher(ConfigUtils.getAppCtx());
        refWatcher.watch(this);
    }

    /**
     * Fragment和Activity解除关联的时候调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e(TAG, "onDetach()");
    }
}
