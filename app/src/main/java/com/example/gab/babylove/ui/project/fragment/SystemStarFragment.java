package com.example.gab.babylove.ui.project.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.entity.ArticleBean;
import com.example.gab.babylove.ui.project.adapter.StarAdapter;
import com.example.gab.babylove.web.WebViewActivity;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.retrofit.BeanModule;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RequestUtils;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/4/20 0020.
 * 項目 TabLayout Fragment
 */

public class SystemStarFragment extends BaseFragment {

    @BindView(R.id.rvLayout)
    RecyclerView mRecyclerView;
    StarAdapter mAdapter;
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    int mPageNo = 0;

    public static SystemStarFragment getInstance(int param1, String param2) {
        SystemStarFragment fragment = new SystemStarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_system_fly;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        Bundle arguments = getArguments();
        int id = arguments.getInt(ARG_PARAM1);
        initRecyle();
        getArticleList(id);

    }

    /**
     * 列表数据加载
     */
    @SuppressLint("CheckResult")
    private void getArticleList(int id) {
//        SpotsDialog dialog = new SpotsDialog(getActivity());dialog.show();
//        IProgressDialog progressDialog = new IProgressDialog().init((AppCompatActivity) getActivity()).setDialogMsg(R.string.loading_get);
        RequestUtils.create(ApiService.class)
                .getArticleList(mPageNo, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetCallBack<BeanModule<ArticleBean>>() {
                    @Override
                    protected void onSuccess(BeanModule<ArticleBean> articleBean) {
                        if (null != articleBean && null != articleBean.getData()) {
                            mAdapter.setNewData(articleBean.getData().getDatas());
                        }
//                        dialog.dismiss();
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }


    private void initRecyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new StarAdapter(R.layout.item_fly, new ArrayList<>());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArticleBean.DatasBean bean = mAdapter.getData().get(position);
            WebViewActivity.startWebActivity(mContext, bean.getLink());// 详情
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
