package com.example.gab.babylove.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.gab.babylove.R;
import com.example.gab.babylove.api.ApiService;
import com.example.gab.babylove.base.BaseActivity;
import com.example.gab.babylove.entity.BookmarkBean;
import com.example.gab.babylove.web.WebViewActivity;
import com.ggz.baselibrary.application.IBaseActivity;
import com.ggz.baselibrary.retrofit.NetCallBack;
import com.ggz.baselibrary.retrofit.RequestUtils;
import com.ggz.baselibrary.retrofit.RxHelper;
import com.ggz.baselibrary.utils.ResourceUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/4/19 0019.
 * 常用网站
 */

public class WebsiteActivity extends BaseActivity implements IBaseActivity {

    @BindView(R.id.id_layout)
    TagFlowLayout mTagFlowLayout;

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_website;
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        getBookmarkList();
    }

    /**
     * 常用网站 数据加载
     */
    @SuppressLint("CheckResult")
    private void getBookmarkList() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RequestUtils.create(ApiService.class)
                .getBookmarkList()
                .compose(RxHelper.handleResult())
                .compose(RxHelper.bindToLifecycle(this))
                .subscribe(new NetCallBack<List<BookmarkBean>>() {
                    @Override
                    protected void onSuccess(List<BookmarkBean> listBeanModule) {
                        if (null != listBeanModule) {
                            initHotKeyData(listBeanModule);
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {

                    }
                });
    }

    /**
     * 热词搜索 设置
     */
    private void initHotKeyData(List<BookmarkBean> listBeanModule) {
        mTagFlowLayout.setAdapter(new TagAdapter(listBeanModule) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv_name = (TextView) LayoutInflater.from(WebsiteActivity.this).inflate(R.layout.item_hotkey_list, parent, false);
                tv_name.setText(listBeanModule.get(position).getName());
                tv_name.setTextColor(ResourceUtils.getRandomColor());
                return tv_name;
            }
        });

        mTagFlowLayout.setOnTagClickListener((view, position, parent) -> {
            WebViewActivity.startWebActivity(this
                    , listBeanModule.get(position).getLink()
                    , listBeanModule.get(position).getId());// 详情
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        });
    }
}
