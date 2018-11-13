package com.example.gab.babylove.ui.main.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.db.BaseRecycleAdapter;
import com.example.gab.babylove.db.DbDao;
import com.example.gab.babylove.db.SearchRecordAdapter;
import com.example.gab.babylove.entity.HotKeyBean;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.KeyBoardUtils;
import com.ggz.baselibrary.utils.L;
import com.ggz.baselibrary.utils.T;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 * 搜索文章
 */
public class SearchArticleActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.id_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.Ll_search)
    LinearLayout Ll_search;
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    String queryKey;
    private SearchRecordAdapter mAdapter;
    private DbDao mDbDao;
    private boolean mHasData;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_search_article;
    }


    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        getHotKeyList();
        mDbDao = new DbDao(this);
        initView();

        //搜索按钮监听
        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!edit_search.getText().toString().trim().equals("")) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mHasData = mDbDao.hasData(edit_search.getText().toString().trim());
                        if (!mHasData) {
                            // 先隐藏键盘
                            ((InputMethodManager) edit_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                            String queryKey = edit_search.getText().toString();
                            Bundle bundle = new Bundle();
                            bundle.putString("queryKey", queryKey);
                            JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
                            mDbDao.insertData(queryKey);
                            mAdapter.updata(mDbDao.queryData(""));
//                    KeyBoardUtils.closeKeyBoard(SearchArticleActivity.this);
                            return true;
                        } else {
                            T.showShort("该内容已在历史记录中");
                        }
                    }
                } else {
                    T.showShort("该内容已在历史记录中");
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_search, R.id.tv_deleteAll})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_search:
                if (!edit_search.getText().toString().trim().equals("")) {
                    if (!mHasData) {
                        mHasData = mDbDao.hasData(edit_search.getText().toString().trim());
                        queryKey = edit_search.getText().toString().trim();
                        Bundle bundle = new Bundle();
                        bundle.putString("queryKey", queryKey);
                        JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
                        KeyBoardUtils.closeKeyBoard(this);
                        mDbDao.insertData(queryKey);
                    } else {
                        T.showShort("该内容已在历史记录中");
                    }
                    mAdapter.updata(mDbDao.queryData(""));
                } else {
                    T.showShort("搜索内容不能为空");
                    return;
                }
                break;

            case R.id.tv_deleteAll:
                mDbDao.deleteData();
                mAdapter.updata(mDbDao.queryData(""));
                break;
        }
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SearchRecordAdapter(mDbDao.queryData(""), this);
        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                mDbDao.delete(mDbDao.queryData("").get(position));
                mAdapter.updata(mDbDao.queryData(""));
            }

            @Override
            public void RvDeleteItemOnclick(int position) {
                T.showShort("123" + position);
            }
        });
//        mAdapter.setRvItemOnclickListener(position -> {
//            mDbDao.delete(mDbDao.queryData("").get(position));
//            mAdapter.updata(mDbDao.queryData(""));
//        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 热词搜索 设置
     */
    private void initHotKeyData(List<HotKeyBean> hotKeyBeans) {
        mTagFlowLayout.setAdapter(new TagAdapter(hotKeyBeans) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv_name = (TextView) LayoutInflater.from(SearchArticleActivity.this).inflate(R.layout.item_navigation_cid, parent, false);
                tv_name.setText(hotKeyBeans.get(position).getName());
                return tv_name;
            }
        });

        mTagFlowLayout.setOnTagClickListener((view, position, parent) -> {
            if (!mHasData) {
                mHasData = mDbDao.hasData(edit_search.getText().toString().trim());
                queryKey = hotKeyBeans.get(position).getName();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey", queryKey);
                JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
                mDbDao.insertData(queryKey);
            } else {
                T.showShort("该内容已在历史记录中");
            }
            mAdapter.updata(mDbDao.queryData(""));
            return true;
        });
    }


    @SuppressLint("CheckResult")
    private void getHotKeyList() {
        RequestUtils.create(ApiService.class)
                .getHotKeyList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<List<HotKeyBean>>() {
                    @Override
                    protected void onSuccess(List<HotKeyBean> hotKeyBeans) {
                        initHotKeyData(hotKeyBeans);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
}
