package com.example.gab.babylove.ui.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gab.babylove.R;
import com.example.gab.babylove.widget.SuperFileLayout;
import com.fy.baselibrary.application.IBaseActivity;
import com.fy.baselibrary.statusbar.MdStatusBar;
import com.fy.baselibrary.utils.L;
import java.io.File;

import butterknife.BindView;


/**
 * 使用tbs 不同格式文件浏览 activity
 * Created by fangs on 2017/12/4.
 */
public class FileBrowsingActivity extends AppCompatActivity implements IBaseActivity {

    @BindView(R.id.mSuperFileView)
    SuperFileLayout mSuperFileView;
    String filePath = "/storage/emulated/0/Download/aas.doc";

    private void getFilePathAndShowFile(SuperFileLayout superFileView) {
        if (filePath.contains("http")) {//网络地址要先下载
//            downLoadFromNet(getFilePath(),mSuperFileView2);
        } else {
            superFileView.displayFile(new File(filePath));
        }
    }

    @Override
    public void onDestroy() {
        L.e("FileDisplayActivity-->onDestroy");

        if (null != mSuperFileView) {
            mSuperFileView.onStopDisplay();
        }
        super.onDestroy();
    }

    @Override
    public boolean isShowHeadView() {
        return true;
    }

    @Override
    public int setView() {
        return R.layout.activity_file_browsing;
    }

    @Override
    public void setStatusBar(Activity activity) {
        MdStatusBar.setColorBar(activity, R.color.statusBar, R.color.statusBar);
    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        mSuperFileView.setOnGetFilePathListener(new SuperFileLayout.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileLayout mSuperFileView) {
                getFilePathAndShowFile(mSuperFileView);
            }
        });
        mSuperFileView.show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
