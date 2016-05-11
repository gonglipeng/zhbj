package com.example.administrator.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.administrator.zhbj.base.BaseContentPage;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class SmartContentPage extends BaseContentPage {

    public SmartContentPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView = new TextView(mAcitivity);
        textView.setText("智慧生活");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        flBaseFragment.addView(textView);
        tv_title.setText("智慧生活");
    }
}
