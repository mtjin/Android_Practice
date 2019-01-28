package com.example.a82107.myservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Intent intent = new Intent (getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);
                startService(intent);
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    //MyService클래스의 onStartCommand처럼 매번 계속 여기서 데이터를 받을 수 있음
    //메인 엑티비티가 이미 만들어져 있는 상태에서는 OnCreate가 아닌 이 함수가 호출됨
    //onCreate에서도 받을 수 있지만 onCreate는 처음한번만 가능하다.(이 메인엑티비티가 처음만들어지는 시점에만)
    private  void processCommand(Intent intent){
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "서비스로부터 받은 데이터 : " + command + ", "+ name, Toast.LENGTH_LONG).show();


        }
    }
}
