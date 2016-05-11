package com.example.administrator.zhbj;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/5/10.
 */
public class TApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
