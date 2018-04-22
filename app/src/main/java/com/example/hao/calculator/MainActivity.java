package com.example.hao.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jupiter on 2016/10/6.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt_0,bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_multiply,bt_divide,bt_add,bt_minus,bt_point,bt_del,bt_equal,bt_clean;
    TextView text_input;
    boolean clear_flag; //清空标识

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_0 = (Button)findViewById(R.id.bt_0);
        bt_1 = (Button)findViewById(R.id.bt_1);
        bt_2 = (Button)findViewById(R.id.bt_2);
        bt_3 = (Button)findViewById(R.id.bt_3);
        bt_4 = (Button)findViewById(R.id.bt_4);
        bt_5 = (Button)findViewById(R.id.bt_5);
        bt_6 = (Button)findViewById(R.id.bt_6);
        bt_7 = (Button)findViewById(R.id.bt_7);
        bt_8 = (Button)findViewById(R.id.bt_8);
        bt_9 = (Button)findViewById(R.id.bt_9);
        bt_multiply = (Button)findViewById(R.id.bt_multiply);
        bt_divide = (Button)findViewById(R.id.bt_divide);
        bt_add = (Button)findViewById(R.id.bt_add);
        bt_minus = (Button)findViewById(R.id.bt_minus);
        bt_point = (Button)findViewById(R.id.bt_point);
        bt_del = (Button)findViewById(R.id.bt_del);
        bt_equal = (Button)findViewById(R.id.bt_equal);
        bt_clean = (Button)findViewById(R.id.bt_clean);

        text_input = (TextView)findViewById(R.id.textView);

        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
        bt_clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = text_input.getText().toString();
        switch (v.getId()){
            case R.id.bt_0:
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_point:
                if(clear_flag){
                    clear_flag=false;
                    str = "";
                    text_input.setText("");
                }
                text_input.setText(str+((Button)v).getText());
                break;

            case R.id.bt_add:
            case R.id.bt_minus:
            case R.id.bt_multiply:
            case R.id.bt_divide:
                if(clear_flag){
                    clear_flag=false;
                    text_input.setText("");
                }
                text_input.setText(str+" "+((Button)v).getText()+" ");  //在每个运算符 前 后 各加一个 空格
                break;

            case R.id.bt_del:
                if(clear_flag){
                    clear_flag=false;
                    str = "";
                    text_input.setText("");
                }else if(str != null && !str.equals("")){
                    text_input.setText(str.substring(0,str.length()-1));
                }
                break;

            case R.id.bt_clean:
                clear_flag=false;
                str = "";
                text_input.setText("");
                break;

            case R.id.bt_equal:
                getResult();
                break;
        }
    }
    //运算结果
    private void getResult(){
        String exp = text_input.getText().toString();
        if(exp == null || exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        String str_1 = exp.substring(0,exp.indexOf(" ")); // 运算符前面的字符
        String str_op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2); //获取到运算符
        String str_2 = exp.substring(exp.indexOf(" ")+ 3);   //运算符后面的数字

        double result = 0;
        if(!str_1.equals("")&&!str_2.equals("")){
            double num_1 = Double.parseDouble(str_1);   //先将str_1、str_1强制转化为double类型
            double num_2 = Double.parseDouble(str_2);

            if (str_op.equals("＋")){
                result = num_1 + num_2;
            }else if (str_op.equals("-")){
                result = num_1 - num_2;
            }else if (str_op.equals("×")){
                result = num_1 * num_2;
            }else if (str_op.equals("÷")){
                if(num_2 == 0){
                    result = 0;
                }else {
                    result = num_1 / num_2;
                }
            }
            if(!str_1.contains(".")&&!str_2.contains(".")&&!str_op.equals("÷")){
                int r = (int) result;
                text_input.setText(r+"");
            }else{
                text_input.setText(result+"");
            }
        }else if(!str_1.equals("")&&str_2.equals("")){
            text_input.setText(exp);
        }else if(str_1.equals("")&&!str_2.equals("")) {
            double num_2 = Double.parseDouble(str_2);
            if (str_op.equals("+")){
                result = 0 + num_2;
            }else if (str_op.equals("-")){
                result = 0 - num_2;
            }else if (str_op.equals("×")){
                result = 0;
            }else if (str_op.equals("÷")){
                result = 0;
            }
            if(!str_2.contains(".")){
                int r = (int) result;
                text_input.setText(r+"");
            }else{
                text_input.setText(result+"");
            }
        }else{
            text_input.setText("");
        }
    }
}