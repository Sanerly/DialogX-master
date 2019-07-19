package com.sanerly.dialogx.core;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sanerly.dialogx.R;
import com.sanerly.dialogx.base.BaseDialog;


/**
 * @Author: Sanerly
 * @CreateDate: 2019/7/8 12:03
 * @Description: 类描述
 */
public class CenterDialog extends BaseDialog {

    TextView slideTitle;
    TextView slideMessage;
    Button slideCancel;

    public CenterDialog(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_center;
    }

    @Override
    public void onCreate() {
        slideCancel = findViewById(R.id.slide_cancel);
        slideCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public long getDuration() {
        return super.getDuration();
    }

    public void setTextCancel(String s) {
        slideCancel.setText(s);
    }
}
