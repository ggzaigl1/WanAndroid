package com.fy.baselibrary.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fy.baselibrary.R;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.DaggerRequestComponent;
import com.fy.baselibrary.retrofit.RequestComponent;
import com.fy.baselibrary.utils.cache.ACache;
import com.werb.permissionschecker.PermissionChecker;

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
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public PermissionChecker permissionChecker;
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Inject
    protected ApiService mConnService;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

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
        permissionChecker = new PermissionChecker(mContext);
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        }
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
    /**
     * 需要权限获取返回结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {

                } else {
                    permissionChecker.showDialog();
                }
                break;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        }
    }
}
