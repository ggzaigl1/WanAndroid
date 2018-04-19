package com.example.gab.babylove.fragment;

import android.support.v7.widget.Toolbar;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.statusbar.MdStatusBar;

import butterknife.BindView;

/**
 * Created by Gab on 2017/12/15 0015.
 * 书签
 */

public class BookmarksFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_bookmarks;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        toolbar.setTitle("书签");
        MdStatusBar.setColorBar(getActivity(), R.color.statusBar, R.color.statusBar);
    }

}
