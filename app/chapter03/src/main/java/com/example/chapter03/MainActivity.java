package com.example.chapter03;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        Button btn = findViewById(R.id.btn_single);
        TextView tv_result = findViewById(R.id.tv_result);
        btn.setOnClickListener(new MyOnClickListener(tv_result));
    }

    static class MyOnClickListener implements View.OnClickListener {
        private final TextView tv_result;
        public MyOnClickListener(TextView tv_result) {
            this.tv_result = tv_result;
        }
        @Override
        public void onClick (View v) {
            String desc = String.format("按钮 %s 被点击了", ((Button) v).getText());
            tv_result.setText(desc);
        }
    }
}
