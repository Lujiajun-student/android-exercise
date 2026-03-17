package com.example.chapter04;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    // 第一个操作数
    private String firstNum = "";

    // 运算符
    private String operator = "";

    // 第二个操作数
    private String secondNum = "";

    // 显示的文本
    private String showText = "";

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        // 获取展示计算结果的视图
        tv_result = findViewById(R.id.tv_result);

        // 给每一个按钮控件注册点击监听器
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String inputText;
        // 获取到当前按钮
        // 开根号按钮
        if (v.getId() == R.id.btn_sqrt) {
            inputText = "✓";
        }else {
            // 其他操作都能够直接获取按钮的文本内容
            inputText = ((TextView) v).getText().toString();
        }
        int id = v.getId();
        if (id == R.id.btn_clear) {
            clear();
        }else if (id == R.id.btn_cancel) {

        }else if (id == R.id.btn_plus) {
            operator = inputText;
            refreshText(showText + operator);
        }else if (id == R.id.btn_minus) {

        }else if (id == R.id.btn_multiply) {

        }else if (id == R.id.btn_divide) {
            operator = inputText;
            refreshText(showText + operator);
        }else if (id == R.id.btn_equal) {

        }else if (id == R.id.btn_sqrt) {

        }else if (id == R.id.btn_reciprocal) {

        }else {
            // 输入操作数时，需要检测运算符是否已经输入，未输入代表当前操作数为第一个操作数，例如89后再点击1就是891
            if (operator.isEmpty()) {
                firstNum += inputText;
            }else {
                // 有运算符，拼接第二个操作数，例如89+后再点击1就是89+1
                secondNum += inputText;
            }
            // 刷新显示文本
            if (showText.equals("0") && !inputText.equals(".")){
                // 如果当前显示是0且输入的不是小数点，则替换显示
                refreshText(inputText);
            } else if (showText.isEmpty()) {
                // 如果显示为空，直接显示输入内容
                refreshText(inputText);
            } else {
                // 否则拼接显示
                refreshText(showText + inputText);
            }

        }
    }
    // 刷新文本显示
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }

    // 刷新运算符
    private void refreshOperate(String new_result) {
        String result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    // 清空
    private void clear() {
        refreshText("");
    }
}
