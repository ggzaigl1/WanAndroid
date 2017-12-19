package com.example.gab.babylove.activity;

import android.os.Bundle;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import butterknife.BindView;
import id.arieridwan.lib.PageLoader;

/**
 * Created by Gab on 2017/12/19 0019.
 * 個人中心
 */

public class PersonalCenterActivity extends BaseActivity {

    @BindView(R.id.pageloader)
    PageLoader mPageLoader;

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mPageLoader.startProgress();
    }
}
