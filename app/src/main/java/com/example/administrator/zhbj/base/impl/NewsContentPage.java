package com.example.administrator.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.administrator.zhbj.MainActivity;
import com.example.administrator.zhbj.base.BaseContentPage;
import com.example.administrator.zhbj.damain.NewsMenu;
import com.example.administrator.zhbj.fragment.SlideFragment;
import com.example.administrator.zhbj.utils.Const;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class NewsContentPage extends BaseContentPage {

    public NewsMenu mNewsMenu;

    public NewsContentPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView = new TextView(mAcitivity);
        textView.setText("新闻中心");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        flBaseFragment.addView(textView);
        tv_title.setText("新闻");

        getHttpJson();
    }

    public void getHttpJson() {
        RequestParams params = new RequestParams(Const.URL_HTTP_HOME+Const.URL_HTTP_MENU_GSON);
        /*params.setSslSocketFactory(); // 设置ssl
        params.addQueryStringParameter("wd", "xUtils");*/
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                /*Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();*/
                Log.i("tag", result);

                progressData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                /*Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onCancelled(CancelledException cex) {
                /*Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void progressData(String json) {
        Gson gson=new Gson();
        mNewsMenu=gson.fromJson(json, NewsMenu.class);

        MainActivity mainActivity=(MainActivity)mAcitivity;
        SlideFragment slideFragment=mainActivity.getSlideFragment();
        Log.i("mNewsMenu",mNewsMenu.toString());
        slideFragment.setMenuData(mNewsMenu);
    }
}
