package com.sanerly.dialogx.anim;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * @Author: Sanerly
 * @CreateDate: 2019/7/9 10:59
 * @Description: 类描述
 */
public class ScaleAnimator extends BaseAnimator {


    @Override
    public void initAnimator() {
        mView.setScaleX(0f);
        mView.setScaleY(0f);
        mView.setAlpha(0);

        // 设置动画参考点
        mView.setPivotX(mView.getMeasuredWidth() >> 1);
        mView.setPivotY(mView.getMeasuredHeight() >> 1);
    }

    @Override
    public void showAnimator() {
        mView.animate().scaleX(1f).scaleY(1f).alpha(1f)
                .setDuration(mDuration)
                .setInterpolator(new OvershootInterpolator(1f))
                .start();
    }

    @Override
    public void dismissAnimator() {
        mView.animate().scaleX(0f).scaleY(0f).alpha(0f).setDuration(mDuration)
                .setInterpolator(new FastOutSlowInInterpolator()).start();
    }
}
