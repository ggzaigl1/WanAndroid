package com.example.gab.babylove.ui.main.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 引导图adapter
 */
public class GuideAdapter extends PagerAdapter {

    private final ImageView[] imgViews;

    public GuideAdapter(ImageView[] imgViews) {
        this.imgViews = imgViews;
    }

    @Override
    public int getCount() {
        return imgViews.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgViews[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgViews[position], 0);
        return imgViews[position];
    }

}