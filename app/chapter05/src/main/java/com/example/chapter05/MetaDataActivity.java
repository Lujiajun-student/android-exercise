package com.example.chapter05;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data);
        TextView tv_meta = findViewById(R.id.tv_meta);
        // 从AndroidManifest获取meta数据
        // 1. 获取包管理器
        PackageManager packageManager = getPackageManager();
        try {
            // 2. 获取当前活动的信息对象
            ActivityInfo info = packageManager.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            // 3. 通过信息对象获取bundle
            Bundle bundle = info.metaData;
            // 4. 元数据保存在bundle中，可以直接获取
            String hello = bundle.getString("say_hello");
            tv_meta.setText(hello);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}