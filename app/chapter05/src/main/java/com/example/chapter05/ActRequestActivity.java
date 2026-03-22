package com.example.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String mRequest = "呵呵哒";

    private ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tv_request = findViewById(R.id.tv_request);
        tv_request.setText("待发送的消息为" + mRequest);

        findViewById(R.id.btn_request).setOnClickListener(this);
        // 通过registerForActivityResult注册对Activity结果的监听，其中Contracts表示是启动另一个Activity并期待返回结果，
        // Callback表示回调接口，目标Activity关闭后，会触发这里的onActivityResult方法
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    Intent intent = result.getData();
                    if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
                        Bundle bundle = intent.getExtras();
                        String response_time = bundle.getString("response_time");
                        String response_content = bundle.getString("response_content");
                        TextView tv_response = findViewById(R.id.tv_response);
                        tv_response.setText("收到回复：" + response_time + "，回复内容为" + response_content);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        // 点击跳转
        Intent intent = new Intent(this, ActResponseActivity.class);
        // 创建bundle
        Bundle bundle = new Bundle();
        // 获取当前时间
        bundle.putString("request_time", System.currentTimeMillis() + "");
        bundle.putString("request_content", mRequest);
        intent.putExtras(bundle);
        // 有了注册器，就能通过launch方法启动目标Activity
        register.launch(intent);

    }
}