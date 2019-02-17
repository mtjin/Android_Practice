package com.example.a82107.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    ValueHandler handler = new ValueHandler();

    //핸들러를 사용한 스레드 사용(메인액티비티의 UI를 메인스레드가 아닌 다른 스레드에서 핸들러를 이용해 접근가능)
    //MyThread , MyThread3 프로젝트와 비교\
    //이것도 실제 개발에서는 이렇게 잘안한다, 왜냐하면 저렇게 메세지를 보내고 핸들러에서 처리하고 하는 과정이 복잡하기떄문이다.
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
                //textView.setText("현재 값 : " + value);
            }
        });
    }

    //스레드 구현한 객체
    class BackgroundThread extends Thread {
        boolean isRun = false;
        int value = 0;
        public void run() {
            isRun = true;
            //1초마다 벨류값 1씩 증가시키는 스레드임
            while ((isRun)) {
                value += 1;

                //obtainMessage로 메세지를 참조하고 메세지에 보낼 데이터를 참조하고 sendMessage해줌
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                //sendMessage가 되면 이 handler가 해당되는 핸들러객체가(ValueHandler) 자동으로 호출된다.
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }
    }

    //핸들러구현한 객체(핸들러역할)
    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value);
        }
    }
}
