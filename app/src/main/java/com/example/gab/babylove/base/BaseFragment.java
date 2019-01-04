package com.example.gab.babylove.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiServiceKotlin;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.LogUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.ggz.baselibrary.utils.T;
import com.ggz.baselibrary.utils.cache.ACache;
import com.kaopiz.kprogresshud.KProgressHUD;

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

    //视图是否已经初始化完毕
    private boolean isViewReady;
    //fragment是否处于可见状态
    private boolean isFragmentVisible;
    //是否已经初始化加载过
    protected boolean isLoaded;

    //是否使用懒加载 (Fragment可见时才进行初始化操作(以下四个方法))
    protected abstract boolean isLazyLoad();

    protected abstract void initView(View view);

    protected abstract int setContentLayout();

    protected abstract void initData();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!NetworkUtils.isNetworkAvailable(ConfigUtils.getAppCtx())) {
            T.showShort("好像没有网络耶~");
        } else {
            if (null == mRootView) {
                mRootView = inflater.inflate(setContentLayout(), container, false);
            } else {
                ViewGroup parent = (ViewGroup) mRootView.getParent();
                if (null != parent) {
                    parent.removeView(mRootView);
                }
            }
        }
        unbinder = ButterKnife.bind(this, mRootView);
        LogUtils.e(TAG, "onCreateView()");
        return mRootView;
    }

    /**
     * //当Activity中的onCreate方法执行完后调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewReady = true;
        if (!isLazyLoad() || isFragmentVisible) {
            init();
        }
    }

    /**
     * tab切换一次，执行一次setUserVisibleHint()方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
        //如果视图准备完毕且Fragment处于可见状态，则开始初始化操作
        if (isLazyLoad() && isViewReady && isFragmentVisible) {
            init();
        }
    }

    private void init() {
        if (!isLoaded) {
            isLoaded = true;
            initView(mRootView);
            initData();
        }
    }


    @Override
    public void onClick(View view) {
    }


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
     *
     * @param view
     * @param id
     */
    protected void getCollectArticle(View view, int id) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiServiceKotlin.class)
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
        RequestUtils.create(ApiServiceKotlin.class)
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


    /**
     * //Fragment和Activity建立关联的时候调用
     *
     * @param context
     */
    @Override
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

    /**
     * //Fragment中的布局被移除时调用
     */
    @Override
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
        isViewReady = false;
        isLoaded = false;
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
