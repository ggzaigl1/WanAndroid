package com.ggz.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 初夏小溪 on 2018/10/19 0019.
 */
public class NightModeConfig {

    private SharedPreferences mSharedPreference;
    private static final String NIGHT_MODE = "Night_Mode";
    public static final String IS_NIGHT_MODE = "Is_Night_Mode";

    private boolean mIsNightMode;

    private SharedPreferences.Editor mEditor;

    private static NightModeConfig sModeConfig;

    public static NightModeConfig getInstance() {

        return sModeConfig != null ? sModeConfig : new NightModeConfig();
    }

    public boolean getNightMode(Context context) {

        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NIGHT_MODE, Context.MODE_PRIVATE);
        }
        mIsNightMode = mSharedPreference.getBoolean(IS_NIGHT_MODE, false);
        return mIsNightMode;
    }

    public void setNightMode(Context context, boolean isNightMode) {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NIGHT_MODE, Context.MODE_PRIVATE);
        }
        mEditor = mSharedPreference.edit();

        mEditor.putBoolean(IS_NIGHT_MODE, isNightMode);
        mEditor.commit();
    }
}
