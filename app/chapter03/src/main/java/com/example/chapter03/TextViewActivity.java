package com.example.chapter03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置页面文件
        setContentView(R.layout.activity_text_view);
        // 获取文本控件
        TextView tv = findViewById(R.id.tv_hello);
        // 通过setText设置文本内容
        tv.setText("你好，世界");
        // 设置文本大小
        tv.setTextSize(50);
    }
}
