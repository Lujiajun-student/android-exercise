package com.example.chapter03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        Button btn = findViewById(R.id.btn_single);
        TextView tv_result = findViewById(R.id.tv_result);
        btn.setOnClickListener(new MyOnClickListener(tv_result));

        Button btn_long_click = findViewById(R.id.btn_long);
        // 使用Lambda表达式设置长按监听器
        btn_long_click.setOnLongClickListener(v -> {
            // 其中的v表示调用该方法的对象，这里指btn_long_click
            String desc = String.format("按钮 %s 被长按了", ((Button) v).getText());
            tv_result.setText(desc);
            return true;
        });

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
