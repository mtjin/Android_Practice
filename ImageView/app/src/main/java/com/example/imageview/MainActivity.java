package com.example.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰의 주소 값을 받아온다.
        img3 = (ImageView)findViewById(R.id.imageView3);
        //이미지를 셋팅한다.
        img3.setImageResource(R.drawable.ic_launcher_foreground);
    }
}
