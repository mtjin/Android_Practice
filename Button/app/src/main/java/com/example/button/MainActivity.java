package com.example.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text1;
    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.textView2);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        button4 = findViewById(R.id.button5);
        //리스너 객체를 생성
        BtnListener1 listener1 = new BtnListener1();
        BtnListener2 listener2 = new BtnListener2();
        BtnListener34 listener34 = new BtnListener34();
        //리스너를 버튼 객체에 설정한다.
        button1.setOnClickListener(listener1);
        button2.setOnClickListener(listener2);
        button3.setOnClickListener(listener34);
        button4.setOnClickListener(listener34);
    }

    class BtnListener1 implements View.OnClickListener{

        @Override
        public void onClick(View v){
            text1.setText("첫 번째 버튼을 눌렀습니다");
        }

    }

    class BtnListener2 implements  View.OnClickListener{

        public void onClick(View v){
            text1.setText("두 번째 버튼을 눌렀습니다");
        }
    }

    //세 번째와 네 번째 버튼과 연결된 리스너
    class BtnListener34 implements  View.OnClickListener{

        public void onClick(View v){
            //이벤트가 발샏된 뷰의 ID값을 추출한다.
            int id = v.getId();
            //id로 분기한다.
            switch (id){
                case R.id.button4:
                    text1.setText("세 번째 버튼을 눌렀습니다");
                    break;
                case R.id.button5:
                    text1.setText("네 번째 버튼을 눌렀습니다");
                    break;
            }
        }
    }

    //다섯번째 버튼을 누르면 호출되는 메서드
    public void btn5Method(View view){
        text1.setText("다섯 번째 버튼을 눌렀습니다.");
    }

    //여섯 번째 버튼을 누르면 호출되는 메서드
    public void btn6Method(View view){
        text1.setText("여섯번째 버튼을 눌렀습니다.");
    }

    //일곱 번째, 여덟번째 버튼을 누르면 호출되는 메서드
    public void btn78Method(View view){
        int id = view.getId();

        switch (id){
            case R.id.button8:
                text1.setText("일곱번쨰 버튼을 눌렀습니다");
                break;
            case R.id.button9:
                text1.setText("여덟번째 버튼을 눌렀습니다");
                break;
        }
    }
}
