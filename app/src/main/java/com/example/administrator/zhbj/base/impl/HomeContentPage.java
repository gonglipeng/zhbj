package com.example.administrator.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zhbj.base.BaseContentPage;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class HomeContentPage extends BaseContentPage {

    public HomeContentPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView=new TextView(mAcitivity);
        textView.setText("首页");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        flBaseFragment.addView(textView);
        ib_menu_title.setVisibility(View.GONE);
        tv_title.setText("首页");
    }

}
