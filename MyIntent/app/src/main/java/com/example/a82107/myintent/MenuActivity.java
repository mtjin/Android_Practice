package com.example.a82107.myintent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //해시처럼 키와  벨류값을 설정 (이 액티비티를 불러준 곳에 데이터를 전달할 용도)
                intent.putExtra("name", "mike");
                //Result_OK는 일종의신호이다. 이상이없는 신호라는걸 의미함
                //이 액티비티가 종료되기전에 이 메소드를 호출함으로써 메인액티비티에 응답을 보낼 수 있다.
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }
}
