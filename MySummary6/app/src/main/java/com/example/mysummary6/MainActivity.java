package com.example.mysummary6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppHelper.openDatabase(getApplicationContext(), "movie");
        AppHelper.createTable("outline");
        //영화상세화면으로 들어가서 각각의 영화 상세정보를 저장할 테이블을 movie라는 테이블로  별도로 만들어주면된다.
        //한줄평같은경우는 comment라는 테이블을 만드는데 이게 디비에서 예약어여서 review라는 테이블이름으로 만들면된다.
    }
}
