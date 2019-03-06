package com.example.a82107.myhttp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//http를 이용해서 데이터를 읽어오는 방식(manifest에 인터넷권한 줘야함)
public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    String urlStr;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 urlStr = editText.getText().toString(); //editText의 내용(주소)를 받아와 저장, 스레드의 run에다가 사용하면 스레드에서 UI접근 때문에 에러가발생

                //스래드객체를 하나정의해서 사용함
                RequestThread thread = new RequestThread();
                thread.start();
            }
        });
    }

    class RequestThread extends Thread{
        public void run(){

            try {
                URL url = new URL(urlStr);
                //HttpURLConnection을 이용해서 요청을 보내고 결과를 받음
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000);//10초동안 기다린 후 응답이 없으면 끝낸다.
                    conn.setRequestMethod("GET"); //get방식으로 요청하겠다
                    conn.setDoInput(true); //둘다 true로 하여 입력과 출력이 다 가능하게함
                    conn.setDoOutput(true); //즉, 서버로 보내는게 output, 서버로부터 받는게 input인데 둘다 가능하게함

                    int resCode = conn.getResponseCode(); //getResponseCode()를 호출하면 연결이 시작되는거다. 리턴되는것은 res코드라는게 리턴된다.

                    /*if(resCode == HttpURLConnection.HTTP_OK)*/ //resCode라고 하는게 정상적인 코드(res)가 넘어온경우, 이 코드를(res) 확인을 한후,
                        //한줄씩 읽어서 화면에 뿌려줄 수도 있다. (이건 HTTP_OK라고 하는 코드일 경우 이걸 가지고 뿌려지는 예시를 살짝 보여준거다.)

                    //위 주석처럼 OK가 아니여도 다른값이 넘어 오더라도 무조건 뿌리도록함(따라서 위 if문 같이 체크를 할 필요가 없음)
                    //BufferedReader는 한줄씩 읽어올 수 있고 InputStreamReader로 받으면됨
                    //연결이 만들어진 객체의(위에코드에서 getResponseCode()의해 연결이 만들어짐) getInputStream 메소드를 호출하면
                    // InputStream 객체를 참조할 수 있으며 이 객체로부터 응답 데이터를 읽어 들임
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }

                        println(line);
                    }

                    reader.close();
                    conn.disconnect();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void println(final String data){
        //스레드에서 println()을 사용하는데 부스레드에서 UI에 접근하면 에러가 발생하므로 handler와 post를 사용해서
        //메인스레드에서 접근하고 변경해주는 방식으로함.
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });

    }
}
