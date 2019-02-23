package com.example.a82107.myserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//인터넷기능을 쓸려면 maifest에  <uses-permission android:name="android.permission.INTERNET"/> 추가해야함
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerThread thread = new ServerThread();
                thread.start();
            }
        });
    }

    class ServerThread extends Thread {
        @Override
        public void run() {
            //클라이언트가 이 포트번호로만 접속을 해야함
            int port = 5001;
            try {
                ServerSocket server = new ServerSocket(port);
                Log.d("ServerThread", "서버가 실행됨.");

                while (true) {//클라이언트 연결 대기상태로 들어가게됨(대기를 하다가 클라이언트가 접속을 하게되면 소캣객체가 반환됨)
                    //이 소캣객체를 통해서 클라이언트가 요청한 데이터를 받을 수 있다.
                    Socket socket = server.accept();
                    /*InetAddress clientHost = socket.getLocalAddress();
                    int clientPort = socket.getPort();*/


                    ObjectInputStream instream = new ObjectInputStream((socket.getInputStream()));
                    Object input = instream.readObject();
                    Log.d("ServerThread", "input : " + input);

                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(input + " from server.");
                    //write를 한후 버퍼에 남아있는게 있을수도 있으므로 flush를 해줌
                    outputStream.flush();
                    Log.d("ServerThread", "output 보냄.");

                    //클라이언트 요청을 다 처리한 후 소캣을 끊어줬음
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
