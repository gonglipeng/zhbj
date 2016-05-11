package com.example.administrator.zhbj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj.utils.PrefUtils;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener {

    private ViewPager viewPager;
    private Button btnStart;
    private ArrayList<ImageView> imageViews;
    private int[] imageIds = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private LinearLayout llContainer;
    private ImageView ivRedPoint;
    // 小红点移动距离
    private int mPointDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        viewPager.setAdapter(new MyViewPagerAdapter());
        addListener();
    }



    private void addListener() {
        viewPager.addOnPageChangeListener(this);
        btnStart.setOnClickListener(this);
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        btnStart = (Button) findViewById(R.id.btn_enter);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
    }

    public void initData() {
        imageViews = new ArrayList();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            imageViews.add(imageView);

            ImageView ivGrayPoint=new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(i>0){
                params.leftMargin=10;
            }
            ivGrayPoint.setImageResource(R.drawable.shape_gray_point);
            ivGrayPoint.setLayoutParams(params);
            llContainer.addView(ivGrayPoint);
        }

        // 计算两个圆点的距离
    // 移动距离=第二个圆点left值 - 第一个圆点left值
    // measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
    // mPointDis = llContainer.getChildAt(1).getLeft()
    // - llContainer.getChildAt(0).getLeft();
    // System.out.println("圆点距离:" + mPointDis);

    // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
    // 视图树

    ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            // 移除监听,避免重复回调
            ivRedPoint.getViewTreeObserver()
                    .removeGlobalOnLayoutListener(this);
            // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            // layout方法执行结束的回调
            mPointDis = llContainer.getChildAt(1).getLeft()
                    - llContainer.getChildAt(0).getLeft();
            System.out.println("圆点距离:" + mPointDis);
        }
    });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //页面滑动回调

        // 更新小红点距离
        int leftMargin = (int) (mPointDis * positionOffset) + position
                * mPointDis;// 计算小红点当前的左边距
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
                .getLayoutParams();
        params.leftMargin = leftMargin;// 修改左边距

        // 重新设置布局参数
        ivRedPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        // 某个页面被选中
        if(position==imageIds.length-1){
            btnStart.setVisibility(View.VISIBLE);
            int height=getWindowManager().getDefaultDisplay().getHeight();
            TranslateAnimation translateAnimation=new TranslateAnimation(0,0,height*18/100,0);
            translateAnimation.setDuration(2000);
            translateAnimation.setFillAfter(true);
            btnStart.startAnimation(translateAnimation);
        }else{
            btnStart.setVisibility(View.GONE);
            btnStart.clearAnimation();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //页面回调状态发生变化
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_enter:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                PrefUtils.setBoolean(this, "is_first_enter", false);    //设置不是第一次启动
                finish();
        }
    }

    @Override
    public void onGlobalLayout() {
        // 移除监听,避免重复回调
        ivRedPoint.getViewTreeObserver()
                .removeGlobalOnLayoutListener(this);
        // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        // layout方法执行结束的回调
        mPointDis = llContainer.getChildAt(1).getLeft()
                - llContainer.getChildAt(0).getLeft();
        System.out.println("圆点距离:" + mPointDis);
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
