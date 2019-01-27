package com.example.a82107.myparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //메인엑티비티에서 전달된 인텐트가 여기저장됨
        Intent passedIntent = getIntent();

        processIntent(passedIntent);
    }

    private  void processIntent(Intent intent){
        if(intent!=null){
            //인텐트에서 엑스트라데이터가 들어가있으니깐 엑스트라데이터를얻어옴
            //여기선 getExtra가아닌 getSerial로 해서 아까 ArrayList를 구현한거를 빼올수있음
            // (ArrayList가 Serializable을 implements 했으므로 가능
            //즉 한가지데이터가아닌 이런 자료구조나 객체를 전달받을떄 사용함
            ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
            if(names != null){
                Toast.makeText(getApplicationContext(), "전달받은 이름 리스트 갯수 : " + names.size(), Toast.LENGTH_LONG).show();

            }

            SimpleData data = intent.getParcelableExtra("data");
            if(data != null){
                Toast.makeText(getApplicationContext(), "전달받은 심플데이터  : " + data.message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
