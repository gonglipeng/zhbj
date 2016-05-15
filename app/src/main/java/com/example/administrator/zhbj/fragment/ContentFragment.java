package com.example.administrator.zhbj.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.zhbj.MainActivity;
import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.base.BaseContentPage;
import com.example.administrator.zhbj.base.impl.GovContentnPage;
import com.example.administrator.zhbj.base.impl.HomeContentPage;
import com.example.administrator.zhbj.base.impl.NewsContentPage;
import com.example.administrator.zhbj.base.impl.SettingContentPage;
import com.example.administrator.zhbj.base.impl.SmartContentPage;
import com.example.administrator.zhbj.view.NoScrollViewPage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class ContentFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private NoScrollViewPage vpContent;
    private RadioGroup radioGroup;
    private ArrayList<BaseContentPage> baseContentPages;    //添加viewPage的内容


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        vpContent = (NoScrollViewPage) view.findViewById(R.id.vp_content);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_bottom_tab);
        return view;
    }

    @Override
    public void initData() {
        baseContentPages = new ArrayList();

        baseContentPages.add(new HomeContentPage(mActivity));
        baseContentPages.add(new NewsContentPage(mActivity));
        baseContentPages.add(new SmartContentPage(mActivity));
        baseContentPages.add(new GovContentnPage(mActivity));
        baseContentPages.add(new SettingContentPage(mActivity));


        vpContent.setAdapter(new MyViewPageAdapter());
        radioGroup.setOnCheckedChangeListener(this);
        vpContent.addOnPageChangeListener(this);

        baseContentPages.get(0).initData();
        //初始化首页时SlidingMenu不能滑动
        ((MainActivity)mActivity).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rd_home:
                vpContent.setCurrentItem(0, false);    //设置没有滑动动画
                break;
            case R.id.rd_news:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.rd_smart:
                vpContent.setCurrentItem(2, false);
                break;
            case R.id.rd_gov:
                vpContent.setCurrentItem(3, false);
                break;
            case R.id.rd_setting:
                vpContent.setCurrentItem(4, false);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //正在滑动
    }

    @Override
    public void onPageSelected(int position) {
        //滑动完成
        //优化加载数据
        BaseContentPage baseContentPage=baseContentPages.get(position);
        baseContentPage.initData();

        MainActivity mainUI= (MainActivity) mActivity;    //先强转
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();    //然后得到SlidingMenu
        //如果是第一个和最后一个不允许SlidingMenu滑动
        if(position==0 || position==baseContentPages.size()-1){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //滑动状态改变
    }


    class MyViewPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return baseContentPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseContentPage baseContentPage = baseContentPages.get(position);
            View view = baseContentPage.view;    //布局
            //baseContentPage.initData();    //数据 （根据viewpager特性会加载两个页面，为了优化性能，所以不在此处加载）
            container.addView(view);

            return view;    //注意：此处是view！！！
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(baseContentPages.get(position).view);
        }
    }

    /**
     * 获取新闻页面
     */
    public NewsContentPage getNewsContentPage() {
        return (NewsContentPage) baseContentPages.get(1);
    }
}
