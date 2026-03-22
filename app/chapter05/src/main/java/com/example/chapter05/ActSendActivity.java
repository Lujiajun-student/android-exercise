package com.example.chapter05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActSendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_send);
       tv_send = findViewById(R.id.tv_send);
       findViewById(R.id.btn_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent  = new Intent(this, ActReceiveActivity.class);
        // 创建bundle用于传输数据
        Bundle bundle = new Bundle();
        // 首先传入当前时间
        bundle.putString("request_time", System.currentTimeMillis() + "");
        bundle.putString("request_content", tv_send.getText().toString());
        // 将bundle对象传入intent
        intent.putExtras(bundle);
        startActivity(intent);
    }
}