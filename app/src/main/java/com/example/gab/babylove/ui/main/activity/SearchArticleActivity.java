package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.HotKeyBean;
import com.example.gab.babylove.ui.main.adapter.SearchAdapter;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.JumpUtils;
import com.ggz.baselibrary.utils.KeyBoardUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/11/8 0008.
 * 搜索文章
 */
public class SearchArticleActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_search)
    LinearLayout Ll_search;
    @BindView(R.id.edit_search)
    EditText edit_search;

    String queryKey;
    SearchAdapter mAdapter;

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
        initRecyleHotKeyList();
        //搜索按钮监听
        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String queryKey = edit_search.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey",queryKey);
                JumpUtils.jump(SearchArticleActivity.this,SearchParticularsActivity.class,bundle);
                KeyBoardUtils.closeKeyBoard(this);
                return true;
            }
            return false;
        });
    }

    @OnClick({R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_search:
                queryKey = edit_search.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey",queryKey);
                JumpUtils.jump(SearchArticleActivity.this,SearchParticularsActivity.class,bundle);
                KeyBoardUtils.closeKeyBoard(this);
                break;
        }
    }

    /**
     * 热词搜索 设置
     */
    private void initRecyleHotKeyList() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SearchAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_name:
                        queryKey = mAdapter.getData().get(position).getName();
                        Bundle bundle = new Bundle();
                        bundle.putString("queryKey",queryKey);
                        JumpUtils.jump(SearchArticleActivity.this,SearchParticularsActivity.class,bundle);
                        break;
                }
            }
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
                        if (null != hotKeyBeans) {
                            mAdapter.setNewData(hotKeyBeans);
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    @Override
    public void finish() {
        super.finish();
        KeyBoardUtils.closeKeyBoard(SearchArticleActivity.this);
    }
}
