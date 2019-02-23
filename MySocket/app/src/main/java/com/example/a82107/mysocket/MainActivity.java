package com.example.a82107.mysocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.SocketHandler;

//MyServer클래스와 통신(클라이언트역할함 이프로젝트는)
//인터넷기능을 쓸려면 manifest에  <uses-permission android:name="android.permission.INTERNET"/> 추가해야함
//UI까지 손댈려면 핸들러를 사용해야한다.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends  Thread{
        public  void run(){
            //어떤 컴퓨터에 접속할지 IP지정(localhost라 하면 자기 자신을 가리킴)
            String host = "localhost";
            //Server와 동일하게 포트 설정해줌
            int port = 5001;

            try {
                Socket socket = new Socket(host, port);
                //얜 순서가 서버와 다르게되있다. 먼저 보내고 받는 순으로 되있음

                //보내기위한 통로를 만듬
                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeObject("안녕하세요!");
                //write를 쓰면 flush를 해주는거 기억해라
                outStream.flush();
                Log.d("ClientThread", "서버로 보냄.");

                //받기위한통로
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                Object input = inStream.readObject();
                Log.d("ClientThread", "받은 데이터 : " + input);

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
