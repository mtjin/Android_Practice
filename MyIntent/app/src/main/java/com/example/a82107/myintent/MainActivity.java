package com.example.a82107.myintent;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                //응답을 받을 경우는 startActivityForResult를 사용한다.
                //아니면 그냥 StartActivity(intent)라 하면된다.
                //즉 둘다 액티비티를 화면에 띄우고  인텐트를 전달해주는 역할
                startActivityForResult(intent, 101);
            }
        });
    }

    //세번째 매개변수에서  메뉴엑티비티에서 보낸 intent를 전달받음
    //리퀘스트코드는 메뉴화면을 띄울떄 전달한 101코드가 메뉴화면으로갓다가 다시 여기로 resultCode로 전달됨.
    //즉 resultCode로 어떤화면으로부터 왔는지 알 수 있음
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + name, Toast.LENGTH_LONG).show();

        }
    }
}
