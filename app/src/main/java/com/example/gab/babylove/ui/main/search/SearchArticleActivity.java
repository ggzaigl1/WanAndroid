package com.example.gab.babylove.ui.main.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    //    @BindView(R.id.rv_title)
//    RecyclerView mRecyclerView;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mTagFlowLayout;
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
        //todo 搜索
        getHotKeyList();
//        initRecyleHotKeyList();
        //搜索按钮监听
        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String queryKey = edit_search.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey", queryKey);
                JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
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
        switch (v.getId()) {
            case R.id.tv_search:
                if (edit_search.getText().toString().equals("")) {
                    T.showShort("搜索内容不能为空");
                    return;
                }
                queryKey = edit_search.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey", queryKey);
                JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
                KeyBoardUtils.closeKeyBoard(this);
                break;
        }
    }

    /**
     * 热词搜索 设置
     */
    private void initHotKeyData(List<HotKeyBean> hotKeyBeans) {
        mTagFlowLayout.setAdapter(new TagAdapter(hotKeyBeans) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv_name = (TextView) LayoutInflater.from(SearchArticleActivity.this).inflate(R.layout.item_hotkey_list, parent, false);
                tv_name.setText(hotKeyBeans.get(position).getName());
                return tv_name;
            }
        });

        mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                queryKey = hotKeyBeans.get(position).getName();
                Bundle bundle = new Bundle();
                bundle.putString("queryKey", queryKey);
                JumpUtils.jumpFade(SearchArticleActivity.this, SearchParticularsActivity.class, bundle);
                return true;
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
                        initHotKeyData(hotKeyBeans);
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }
}
