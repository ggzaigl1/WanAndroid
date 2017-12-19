package com.example.gab.babylove.tbs;

import android.os.Bundle;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.L;
import java.io.File;

import butterknife.BindView;


/**
 * 使用tbs 不同格式文件浏览 activity
 * Created by fangs on 2017/12/4.
 */
public class FileBrowsingActivity extends BaseActivity {

    @BindView(R.id.mSuperFileView)
    SuperFileLayout mSuperFileView;
    String filePath = "/storage/emulated/0/Download/aas.doc";


    @Override
    protected int getContentView() {
        return R.layout.activity_file_browsing;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mSuperFileView.setOnGetFilePathListener(new SuperFileLayout.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileLayout mSuperFileView) {
                getFilePathAndShowFile(mSuperFileView);
            }
        });
        mSuperFileView.show();
    }

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
}
