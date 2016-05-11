package com.example.administrator.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class NoScrollViewPage extends ViewPager {

    public NoScrollViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPage(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
