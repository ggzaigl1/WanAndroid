package com.fy.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.DaggerRequestComponent;
import com.fy.baselibrary.retrofit.RequestComponent;
import com.fy.baselibrary.utils.cache.ACache;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 * Created by fangs on 2017/4/26.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected ACache mCache;

    protected BaseActivity mContext;
    protected View mRootView;
    protected Unbinder unbinder;

    @Inject
    protected ApiService mConnService;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        RequestComponent component = DaggerRequestComponent.builder().build();
        component.inJect(this);

        this.mContext = (BaseActivity) context;
        mCache = ACache.get(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);

            baseInitView();
            baseInit();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onClick(View view) {
    }

    protected void baseInitView() {
    }

    protected void baseInit() {
    }

    protected abstract int getContentLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != unbinder){
            unbinder.unbind();
        }
    }
    //Fragment生命周期管理
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
            onPause();
        } else {// 重新显示到最前端中
            onResume();
        }
    }

}
