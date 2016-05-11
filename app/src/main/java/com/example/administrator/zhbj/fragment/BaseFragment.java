package com.example.administrator.zhbj.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zhbj.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;

    //Fragment创建
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this.getActivity();
    }

    //Fragment布局初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=initView();
        return view;

    }

    //activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //初始化Fragment布局
    public abstract View initView();

    //初始化Fragment数据
    public abstract  void initData();

    public void toggle(){
        MainActivity mainActivity=(MainActivity)mActivity;
        SlidingMenu menu=mainActivity.getSlidingMenu();
        menu.toggle();    //如果slidingmenu关了就打开，如果打开了就关闭
    }
}
