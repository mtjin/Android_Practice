package com.example.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //뷰의 주소값을 담을 참조 변수를 선언
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id속성이 text2인 뷰의 주소값을 얻어온다.
        text1 = findViewById(R.id.text2);
        text1.setText("새로운 문자열");
    }
}
