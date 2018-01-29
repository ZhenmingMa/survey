package com.cby.orange.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.cby.orange.app.Orange;

/**
 * Created by Ma on 2017/11/28.
 */

public class DimenUtils {

    public static int getScreenWidth(){
        final Resources resources = Orange.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Orange.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
