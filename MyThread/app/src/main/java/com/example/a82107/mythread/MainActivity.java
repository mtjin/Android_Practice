package com.example.a82107.mythread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("현재 값 : " + value);
            }
        });
    }

    //스레드 구현한 객체
    class BackgroundThread extends Thread {
        boolean isRun = false;

        public void run() {
            isRun = true;
            //1초마다 벨류값 1씩 증가시키는 스레드임
            while ((isRun)) {
                value += 1;
                //textView.setText("현재 값 : " + value); 만약에 여기서 button2같은 텍스트UI 값을 바꿔주는 걸 한다면
                //에러가 발생한다. (메인액티비티는 메인스레드가 이미 점유하고있기 떄문에 다른 스레드에서 접근을 하면 데드락이 걸려 에러가생김
                //그래서 보통 이런식으로 활용을 잘안하고 핸들러를 사용해서 스레드를 활용한다. (MyThread2에서 확인)
                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }
    }
}
