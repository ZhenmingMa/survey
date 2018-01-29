package com.cby.orange.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by baiyanfang on 2018/1/17.
 */

public class OrangeViewFinderView extends ViewFinderView {

    public OrangeViewFinderView(Context context) {
        this(context, null);
    }

    public OrangeViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //扫描框设置为正方形
        mSquareViewFinder = true;
        //设置边框颜色
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);

    }
}
