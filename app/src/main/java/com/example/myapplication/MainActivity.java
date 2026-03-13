package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity 继承AppCompatActivity，能够适配不同版本的Android
public class MainActivity extends AppCompatActivity {

    // onCreate Activity的生命周期方法，页面被创建时自动调用onCreate方法。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 通过super运行父类的初始化代码
        super.onCreate(savedInstanceState);
        // 在页面加载布局文件activity_main.xml
        setContentView(R.layout.activity_main);
        // 从布局文件中找到id为tv的TextView控件
        TextView tv = findViewById(R.id.tv);
        // 修改控件的文本内容
        tv.setText("你好，世界");

        // 实现点击跳转
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
