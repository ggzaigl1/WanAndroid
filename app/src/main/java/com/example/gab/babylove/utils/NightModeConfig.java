package com.example.gab.babylove.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 初夏小溪 on 2018/4/25 0025.
 */

public class NightModeConfig {

    private SharedPreferences mSharedPreference;
    private static final String NIGHT_MODE = "Night_Mode";
    public static final String IS_NIGHT_MODE = "Is_Night_Mode";

    private static NightModeConfig sModeConfig;

    public static NightModeConfig getInstance() {

        return sModeConfig != null ? sModeConfig : new NightModeConfig();
    }

    public boolean getNightMode(Context context) {

        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NIGHT_MODE, context.MODE_PRIVATE);
        }
        boolean isNightMode = mSharedPreference.getBoolean(IS_NIGHT_MODE, false);
        return isNightMode;
    }

    public void setNightMode(Context context, boolean isNightMode) {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NIGHT_MODE, context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreference.edit();

        editor.putBoolean(IS_NIGHT_MODE, isNightMode);
        editor.commit();
    }
}
