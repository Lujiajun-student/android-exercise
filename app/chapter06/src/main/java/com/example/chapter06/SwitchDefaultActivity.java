package com.example.chapter06;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SwitchDefaultActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_default);
        Switch sw_status = findViewById(R.id.sw_status);
        // 要求：当Switch开关状态改变时， TextView显示当前开关状态
        tv_result = findViewById(R.id.tv_result);
        sw_status.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("Switch开关状态：%s", isChecked ? "开启" : "关闭");
        tv_result.setText(desc);
    }
}