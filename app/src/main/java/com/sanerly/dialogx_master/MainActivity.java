package com.sanerly.dialogx_master;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.sanerly.dialogx.core.CenterDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    private CenterDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_dialog);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        dialog = new CenterDialog(this);
        dialog.show();


        dialog.setTextCancel("你确定？");
    }
}
