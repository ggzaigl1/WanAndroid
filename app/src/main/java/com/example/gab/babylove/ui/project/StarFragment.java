package com.example.gab.babylove.ui.project;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ProjectBean;
import com.example.gab.babylove.base.BaseFragment;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.retrofit.ioc.ConfigUtils;
import com.ggz.baselibrary.utils.NetworkUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 * @author Gab
 * @date 2017/12/15 0015
 * 项目
 */

public class StarFragment extends BaseFragment {

    @BindView(R.id.sliding_tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager mViewPager;
    private MyPagerAdapter mAdapter;
    private ArrayList<SystemStarFragment> mFragments = new ArrayList<>();
    List<ProjectBean> data;

    @Override
    protected void baseInit() {
        super.baseInit();
        getArticleList();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_other;
    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getProject()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(getActivity()))
                .subscribe(new NetCallBack<List<ProjectBean>>() {
                    @Override
                    protected void onSuccess(List<ProjectBean> beanModule) {
                        if (null != beanModule) {
                            data = beanModule;
                            for (ProjectBean bean : data) {
                                SystemStarFragment systemFlyFragment = SystemStarFragment.getInstance(bean.getId(), "");
                                mFragments.add(systemFlyFragment);
                            }
                            mAdapter = new MyPagerAdapter(getFragmentManager());
                            mViewPager.setAdapter(mAdapter);
                            mTabLayout.setupWithViewPager(mViewPager);
                        }
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
