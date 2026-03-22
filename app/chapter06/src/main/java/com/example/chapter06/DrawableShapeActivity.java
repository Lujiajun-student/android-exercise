package com.example.chapter06;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DrawableShapeActivity extends AppCompatActivity implements View.OnClickListener{

    private View v_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawable_shape);

        v_content = findViewById(R.id.v_content);

        findViewById(R.id.btn_rect).setOnClickListener(this);
        findViewById(R.id.btn_oval).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 点击相应按钮，使得v_content的背景切换为对应的形状
        if (v.getId() == R.id.btn_rect) {

            v_content.setBackgroundResource(R.drawable.shape_rect_gold);

        } else if (v.getId() == R.id.btn_oval) {

            v_content.setBackgroundResource(R.drawable.shape_oval_rose);

        }
    }
}