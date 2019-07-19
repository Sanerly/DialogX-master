package com.sanerly.dialogx.anim;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;

/**
 * Description: 背景Shadow动画器，负责执行半透明的渐入渐出动画
 * Create by dance, at 2018/12/9
 */
public class BackColorAnimator extends BaseAnimator {

    private ArgbEvaluator evaluator = new ArgbEvaluator();
    private int startColor = Color.TRANSPARENT;
    private int backColor;
    private Animator.AnimatorListener listener;

    public BackColorAnimator(String backColor) {
        this.backColor = Color.parseColor(backColor);
    }

    @Override
    public void initAnimator() {
        mView.setBackgroundColor(startColor);
    }

    @Override
    public void showAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, startColor, backColor);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mView.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.setDuration(mDuration).start();
    }

    @Override
    public void dismissAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, backColor, startColor);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mView.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.setDuration(mDuration).start();

        animator.addListener(listener);

    }

    public void addListener(Animator.AnimatorListener listener) {
        this.listener=listener;
    }

}
