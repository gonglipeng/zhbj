package com.example.administrator.zhbj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.zhbj.MainActivity;
import com.example.administrator.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class BaseContentPage implements View.OnClickListener{

    public Activity mAcitivity;
    public View view;
    public FrameLayout flBaseFragment;
    public TextView tv_title;
    public ImageButton ib_menu_title;

    public BaseContentPage(Activity activity) {
        mAcitivity = activity;
        view = initView();
    }

    private View initView() {
        View view = View.inflate(mAcitivity,R.layout.base_page,null);
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        ib_menu_title= (ImageButton) view.findViewById(R.id.iv_title_menu);
        flBaseFragment= (FrameLayout) view.findViewById(R.id.fl_base_fragment);

        ib_menu_title.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        toggle();
    }

    public void toggle(){
        MainActivity mainActivity=(MainActivity)mAcitivity;
        SlidingMenu menu=mainActivity.getSlidingMenu();
        menu.toggle();    //如果slidingmenu关了就打开，如果打开了就关闭
    }

    public void initData(){}
}
