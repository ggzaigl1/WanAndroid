package com.example.gab.babylove.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gab.babylove.R;
import com.example.gab.babylove.modify.ModifyInfoActivity;
import com.example.gab.babylove.utils.CleanMessageUtil;
import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/12/15 0015.
 * 其他
 */

public class OtherFragment extends BaseFragment {

    @BindView(R.id.imgHead)
    AppCompatImageView imgHead;
    @BindView(R.id.night_switch)
    Switch night_switch;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;
    @BindView(R.id.Ll_cache)
    LinearLayout Ll_cache;


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                //在主线程中需要执行的操作，一般是UI操作
                tv_cache_size.setText("0K");
            }
        }
    };

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_other;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        tvAge.setText(getSpann(R.string.age, "25"));
        tvHeight.setText(getSpann(R.string.height, "165"));
        tvWeight.setText(getSpann(R.string.weight, "63"));
        ImgLoadUtils.loadCircleImg("http://img1.3lian.com/2015/w22/87/d/102.jpg", imgHead);

        try {
            tv_cache_size.setText(CleanMessageUtil.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 Spannable 动态设置 textview 显示内容
     *
     * @param id
     * @param replaceStr
     * @return
     */
    private Spannable getSpann(int id, String replaceStr) {
        Spannable sp = new SpannableString(ResourceUtils.getText(id, replaceStr));
        sp.setSpan(new AbsoluteSizeSpan(21, true), 0, replaceStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.login)), 0, replaceStr.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(14, true), replaceStr.length(), sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return sp;
    }

    @OnClick({R.id.night_switch, R.id.tvEdit, R.id.Ll_cache,R.id.Ll_praise})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.night_switch:
                Switch sw = (Switch) view;
                boolean isChecked = sw.isChecked();
                if (isChecked) {
                    T.showShort("开启");
                } else {
                    T.showShort("关闭");
                }
                break;
            case R.id.tvEdit:
                JumpUtils.jump(mContext, ModifyInfoActivity.class, null);
                break;
            case R.id.Ll_praise:
                startMarket();
                break;
            case R.id.Ll_cache:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle("确认清除")
                        .setMessage("是否清除缓存")
                        .setCancelable(false)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        CleanMessageUtil.clearAllCache(getContext().getApplicationContext());
                                        mHandler.sendEmptyMessage(0);
                                    }
                                }).start();

                            }
                        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
                break;
        }
    }

    public void startMarket() {
        Uri uri = Uri.parse(String.format("market://details?id=%s", getActivity().getPackageName()));
        if (isIntentSafe(getActivity(), uri)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(intent);
        }
        // 没有安装市场
        else {
            Toast.makeText(getContext(), "无法打开应用市场", Toast.LENGTH_SHORT).show();

        }
    }

    public static boolean isIntentSafe(Activity activity, Uri uri) {
        Intent mapCall = new Intent(Intent.ACTION_VIEW, uri);
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapCall, 0);
        return activities.size() > 0;
    }
}
