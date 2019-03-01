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
                //해시처럼 키와  벨류값을 설정
                intent.putExtra("name", "mike");
                //Result_OK는 일종의신호이다. 이상이없는 신호라는걸 의미함
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }
}
