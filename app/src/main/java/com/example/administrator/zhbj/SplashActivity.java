package com.example.administrator.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj.utils.PrefUtils;



public class SplashActivity extends Activity {


    ImageView ivImageView;
    RelativeLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消标题    注意 要继承Activity才有效
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        llRoot = (RelativeLayout) findViewById(R.id.llRoot);
        initAnimation();

    }

    private void initAnimation() {
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,                                                            //0度
                360,                                                          //360度
                Animation.RELATIVE_TO_SELF,                                   //相对自身
                0.5f,                                                         //x中点
                Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(1000);                                    //持续时间
        rotateAnimation.setFillAfter(true);                                   //保持动画完成后状态

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        //动画集合
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        llRoot.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断是不是第一次启动
                boolean isFirstEnter = PrefUtils.getBoolean(SplashActivity.this, "is_first_enter", true);
                Intent intent;
                if (isFirstEnter) {
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
