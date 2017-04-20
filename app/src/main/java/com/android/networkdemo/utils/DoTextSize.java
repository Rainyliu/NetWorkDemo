package com.android.networkdemo.utils;

import android.util.TypedValue;
import android.widget.TextView;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:35
 */

public class DoTextSize {
    public static void setTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX,(int) (size * GlobalConsts.Ry));
    }

    public static void setTextSizePx(TextView view, float size) {
        // view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * GlobalConsts.Ry);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size * GlobalConsts.Rx);
    }
}
