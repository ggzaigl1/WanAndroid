package com.example.gab.babylove.animation;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView mListView;

    @Override
    protected int getContentView() {
        return R.layout.list_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("慕课网" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mListView.setLayoutAnimation(lac);
        mListView.startLayoutAnimation();
    }

}
