package com.sanerly.dialogx.base;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.sanerly.dialogx.anim.BackColorAnimator;
import com.sanerly.dialogx.anim.BaseAnimator;
import com.sanerly.dialogx.anim.ScaleAnimator;
import com.sanerly.dialogx.utils.ScreenUtils;

/**
 * @Author: Sanerly
 * @CreateDate: 2019/7/8 11:43
 * @Description: 基本框架
 */
public abstract class BaseDialog extends FrameLayout {

    public static final String TAG = "BaseDialog";

    private View mChildView;
    private LayoutParams mChildParams;

    private ViewGroup mDecorView;
    private String mBackColor = "#80000000";
    private int moveSlop;

    private BaseAnimator mAnimator;
    private BackColorAnimator backColorAnimator;
    private long mDuration = 500;

    public BaseDialog(Context context) {
        super(context);
        init(context);
        onCreateView();
        onCreate();
    }


    /**
     * 初始化
     */
    private void init(Context context) {
        moveSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        Activity activity = (Activity) getContext();
        mDecorView = (ViewGroup) activity.getWindow().getDecorView();

        LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        int rotation = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_0) {
            params.leftMargin = 0;
            params.rightMargin = 0;
            params.bottomMargin = ScreenUtils.getNavigationBarHeight(getContext());
        } else if (rotation == Surface.ROTATION_90) {
            params.bottomMargin = 0;
            params.rightMargin = ScreenUtils.getNavigationBarHeight(getContext());
            params.leftMargin = 0;
        } else if (rotation == Surface.ROTATION_180) {
            params.bottomMargin = 0;
            params.leftMargin = 0;
            params.rightMargin = ScreenUtils.getNavigationBarHeight(getContext());
        }
        params.topMargin = ScreenUtils.getStatusBarHeight();
        mDecorView.addView(this, params);
//        this.setBackgroundColor(Color.parseColor(mBackColor));
        this.setVisibility(GONE);
        backColorAnimator=new BackColorAnimator(mBackColor);
        backColorAnimator.init(this, mDuration);
        backColorAnimator.addListener(mListener);
    }

    /**
     * 创建子View
     */
    private void onCreateView() {
        mChildView = LayoutInflater.from(getContext()).inflate(getLayoutResId(), this, false);
        mChildParams = new LayoutParams(getChildWidth(), getChildHeight());
        mChildParams.gravity = getGravity();
        mChildView.setLayoutParams(mChildParams);
        this.addView(mChildView);
        mAnimator = getApplyAnimator();
        mAnimator.init(mChildView, getDuration());
    }


    public View getChildView() {
        return mChildView;
    }

    public int getChildWidth() {
        return LayoutParams.WRAP_CONTENT;
    }

    public int getChildHeight() {
        return LayoutParams.WRAP_CONTENT;
    }


    public void setRootBackColor(String backColor) {
        setBackgroundColor(Color.parseColor(backColor));
    }

    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }


    public void show() {
        this.setVisibility(VISIBLE);
        backColorAnimator.showAnimator();
        mAnimator.showAnimator();
    }

    public void dismiss() {
        backColorAnimator.dismissAnimator();
        mAnimator.dismissAnimator();

    }

    public void onDestroy() {
        if (mDecorView != null) {
            mDecorView.removeView(this);
        }
    }


    public BaseAnimator getApplyAnimator() {
        return new ScaleAnimator();
    }

    public long getDuration() {
        return mDuration;
    }


    private Animator.AnimatorListener mListener=new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            onDestroy();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect rect = new Rect();
        getChildView().getGlobalVisibleRect(rect);
        Region region = new Region();
        region.set(rect.left, rect.top - ScreenUtils.getStatusBarHeight(), rect.right, rect.bottom);
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (!region.contains(x, y)) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                float dx = event.getX() - x;
                float dy = event.getY() - y;
                float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                if (distance < moveSlop && isShowing()) {
                    dismiss();
                }
            }
        }
        return true;
    }

    public abstract void onCreate();

    public abstract int getLayoutResId();

    public abstract int getGravity();
}
