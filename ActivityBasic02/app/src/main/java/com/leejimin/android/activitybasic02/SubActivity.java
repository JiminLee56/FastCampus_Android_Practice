package com.leejimin.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;

public class SubActivity extends AppCompatActivity {

    EditText et;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button2);
    // 안드로이드 액티비티에서 값 주고받기

    // 1. 전달받은 인텐트를 가져온다
    Intent intent = getIntent();
    // 2. intent 에서 extra 꾸러미를 꺼낸다
    Bundle bundle = intent.getExtras();
    // 3. 꾸러미에서 key값을 찾아 원하는 값을 꺼낸다
    String str = bundle.getString("key1");

    et.setText(eval(str));

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 1. 에디트텍스트에서 값을 가져온다
            String result = et.getText().toString();

            // 2. 인텐트를 생성하고 돌려줄 값을 세팅한다
            Intent intent = new Intent();
            intent.putExtra("return1", result);
            intent.putExtra("return2", "결과값2");

            // 3. setResult에 결과코드와 데이터를 넘겨준다
            setResult(1, intent);
            // 현재 액티비티 종료
            finish();
        }
    });
}

    // 문자열을 수식으로 변경한 후 결과값을 리턴하는 함수
    // 계산기에서 사용하시면 됩니다
    //사용법 주요사항
    // 핸드폰 키보드에서 입력받은 곱하기, 나누기 등은 특수문자로 인식되어 연산되지 않음
    // 따라서 나누기 -> / , 곱하기 -> * 로 변경하여야 한다
    public String eval(String str){
        String result = "연산식오류";
        try {
            // 1. jexl 엔진 생성
            JexlEngine jexl = new JexlEngine();
            // 2. 계산식이 담긴 문자열을 jexl 에 담아준다
            Expression e = jexl.createExpression(str);
            // 3. evaluate 함수를 통해 연산을 하고 "" 를 붙여서 문자열로 변경한다
            result = e.evaluate(null) + "";
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }



}
