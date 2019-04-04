package com.example.mysplash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/*
* 스플래시 화면

스플래시 화면은 메인 화면이 보이기 전에 보이는 화면입니다.

화면 단위이므로 액티비티로 만들 수 있죠.

앱이 실행되었을 때 어떤 액티비티가 처음 보이도록 할 것인지는 매니페스트 파일(AndroidManifest.xml)에서 설정합니다

따라서 매니페스트 파일을 열고 처음 보일 액티비티를 스플래시를 위한 액티비티로 설정해야 합니다.
* */

//매니페스트에서 이 액티비티가 초기화면에 나오게 설정하였따.
public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish(); //메인액티비티에서 뒤로가기했을때 이화면이 다시또안뜨고 바로 종료하게하기위해 finish넣어줌
            }
        }, 1000);
    }
}
