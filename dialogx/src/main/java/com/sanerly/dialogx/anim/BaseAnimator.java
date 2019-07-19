package com.sanerly.dialogx.anim;

import android.view.View;

/**
 * @Author: Sanerly
 * @CreateDate: 2019/7/9 10:48
 * @Description: 类描述
 */
public abstract class BaseAnimator {
    protected View mView;
    protected long mDuration;

    public BaseAnimator() {
    }


    public void init(View view, long duration) {
        this.mView = view;
        this.mDuration = duration;

    }

    public abstract void initAnimator();

    public abstract void showAnimator();

    public abstract void dismissAnimator();
}
