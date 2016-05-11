package com.example.administrator.zhbj;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.administrator.zhbj.fragment.ContentFragment;
import com.example.administrator.zhbj.fragment.SlideFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity{

    private final String TAG_CONTENT_FRAGMENT="TAG_CONTENT_FRAGMENT";
    private final String TAG_LEFT_FRAGMENT="TAG_LEFT_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);    //设置布局
        int width=getWindowManager().getDefaultDisplay().getWidth();    //得到宽度
        SlidingMenu menu=getSlidingMenu();
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);    //全屏触摸
        menu.setBehindOffset(width * 18 / 100);    //屏幕预留距离

        initFragment();
    }

    private void initFragment() {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT_FRAGMENT);
        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记
        transaction.replace(R.id.left_menu, new SlideFragment(), TAG_LEFT_FRAGMENT);
        transaction.commit();
    }

    public SlideFragment getSlideFragment(){
        FragmentManager fm=getSupportFragmentManager();
        SlideFragment slideFragment = (SlideFragment) fm.findFragmentByTag(TAG_LEFT_FRAGMENT);
        return slideFragment;
    }

    public ContentFragment getContentFragment(){
        FragmentManager fm=getSupportFragmentManager();
        ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT_FRAGMENT);
        return contentFragment;
    }

}
