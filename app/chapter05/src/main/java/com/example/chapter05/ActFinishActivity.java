package com.example.chapter05;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActFinishActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_finish);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        // 点击这两个按钮，实现关闭页面
        if (v.getId() == R.id.iv_back || v.getId() == R.id.btn_finish) {
            finish();
        }
    }
}