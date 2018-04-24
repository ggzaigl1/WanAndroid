package com.example.gab.babylove.ui.project.fragment;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ProjectBean;
import com.example.gab.babylove.ui.news.fragment.SystemFlyFragment;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.statusbar.MdStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gab on 2017/12/15 0015.
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
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
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
        IProgressDialog progressDialog = new IProgressDialog().init((AppCompatActivity) getActivity()).setDialogMsg(R.string.loading_get);
        RequestUtils.create(ApiService.class)
                .getProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<BeanModule<List<ProjectBean>>>(progressDialog) {
                    @Override
                    protected void onSuccess(BeanModule<List<ProjectBean>> beanModule) {
                        if (null != beanModule && null != beanModule.getData()) {
                            data = beanModule.getData();
                            for (ProjectBean bean : data) {
                                SystemStarFragment systemFlyFragment = SystemStarFragment.getInstance(bean.getId(), "");
                                mFragments.add(systemFlyFragment);
                            }
                            mAdapter = new MyPagerAdapter(getFragmentManager());
                            mViewPager.setAdapter(mAdapter);
                            mTabLayout.setupWithViewPager(mViewPager);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
