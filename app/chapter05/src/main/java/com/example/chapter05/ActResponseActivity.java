package com.example.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {

    private final String mResponse = "OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tv_request = findViewById(R.id.tv_request);
        // 获取上一个页面传入的意图
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String desc = String.format("收到请求信息：\n请求时间：%s\n请求内容：%s", request_time, request_content);
        // 将请求信息显示在 TextView 中
        tv_request.setText(desc);
        findViewById(R.id.btn_response).setOnClickListener(this);
        TextView tv_response = findViewById(R.id.tv_response);
        tv_response.setText("待返回的消息：" + mResponse);
    }

    @Override
    public void onClick(View v) {
        // 点击返回上一页，并携带数据
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        // 将数据保存在bundle中
        bundle.putString("response_time", System.currentTimeMillis() + "");
        bundle.putString("response_content", mResponse);
        intent.putExtras(bundle);
        // 携带意图返回上一级页面
        setResult(Activity.RESULT_OK, intent);
        // 结束页面
        finish();
    }
}