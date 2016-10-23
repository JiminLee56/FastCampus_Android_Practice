package com.leejimin.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button btn;
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트를 이용해서 액티비티에 값 전달하기

                // 1. 인텐트 생성
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                String str = et.getText().toString();

                //2. 인텐트에 putExtra로 key 와 값을 전달
                intent.putExtra("key1", str);
                intent.putExtra("key2", "홍길동");
                intent.putExtra("key3", 10000);

                // 3. 호출한 액티비티에서 다시 값을 받을때는 아래 함수 호출 - 그냥 호출만 할경우는 startActivity(intent)
                //    아래 함수로 액티비티를 호출하면 해당 액티비티가 종료후
                //    현재 액티비티의 onActivityResult 함수를 호출해준다
                startActivityForResult(intent, 0);

            }
        });

    }


    @Override                            //    ↓ 요청한 버튼을 구별하기 위한 코드 (버튼이 여러개 있을 경우 해당 버튼을 구별하기 위해 사용)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           //    호출된 액티비티에서 다시 리턴해주는 결과 코드  ↑ (주로 성공 1, 실패 0 으로 세팅되서 받게된다)
                                                                        //   ↑ 액티비티에서 넘겨준 데이터
        super.onActivityResult(requestCode, resultCode, data);

        // Send 버튼에서 호출할 때 넘겨줬던 코드
        if(requestCode == 0){
            // 호출됐던 액티비티에서 다시 리턴해준 결과 코드
            if(resultCode == 1){
                Bundle bundle = data.getExtras();
                String result = bundle.getString("return1");
                et.setText(result);

            }
        }


    }




}

