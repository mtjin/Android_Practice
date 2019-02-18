package com.example.a82107.mythread3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    //스레드 내부에서 쓸 수 있는 핸들러를 전역으로 정의
    //ValueHandler handler = new ValueHandler();

    Handler handler2 = new Handler();


    //MyThread2에비해 더 간단해짐
    //실제로 가장 많이 쓰임
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BackgroundThread thread = new BackgroundThread();
                thread.start();*/

                //Runable객체를 implent하는방법(이것을 구현함으로써 한번 실행될 객체를 정의가능)
                //스레드를 만들고 그안에 Runnable을 집어넣는데 myThread2에서 스레드를 클래스로 별도로
                //만들었을떄와 차이가 없음.
                new Thread(new Runnable() {
                    boolean isRun = false;
                    int value = 0;

                    @Override
                    public void run() {
                        isRun = true;
                        //1초마다 벨류값 1씩 증가시키는 스레드임
                        while ((isRun)) {
                            value += 1;
                            //핸들러클래스로서 post로 던질수가있음.
                            //핸들러의 post 메소드를 호출하면 Runnable 객체를 전달할 수 있습니다.
                            //핸들러로 전달된 Runnable, 객체는 메인 스레드에서 실행될 수 있으며 따라서 UI를 접근하는 코드는 Runnable 객체 안에 넣어두면 됩니다.
                            //post 메소드 이외에도 지정된 시간에 실행하는 postAtTime 메소드와 지정된 시간만큼 딜레이된 시간후 실행되는 postDelayed 메소드가 있습니다.
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("현재값 : " + value);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start(); //start()붙이면 바로실행시킨다.
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

    /*//스레드 구현한 객체
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
    }*/

    /*//핸들러구현한 객체(핸들러역할)
    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value);
        }
    }*/
}
