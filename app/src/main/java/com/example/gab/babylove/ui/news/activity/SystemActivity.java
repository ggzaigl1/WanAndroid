package com.example.gab.babylove.ui.news.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.entity.TreeBean;
import com.example.gab.babylove.ui.news.fragment.SystemFlyFragment;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/4/20 0020.
 * 知识体系 详情页
 */

public class SystemActivity extends AppCompatActivity implements IBaseActivity {

    private ArrayList<SystemFlyFragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private List<TreeBean.ChildrenBean> mChildren;
    TreeBean bean;

    @BindView(R.id.sliding_tab)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager mViewPager;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_system;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        bean = (TreeBean) getIntent().getSerializableExtra("bean");
        mChildren = bean.getChildren();
        for (TreeBean.ChildrenBean child : mChildren) {
            SystemFlyFragment systemFlyFragment = SystemFlyFragment.getInstance(child.getId(), "");
            mFragments.add(systemFlyFragment);
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

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
            return mChildren.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
