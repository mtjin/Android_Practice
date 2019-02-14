package com.example.a82107.mysummary4_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//인터페이스 구현
public class MainActivity extends AppCompatActivity implements FragmentCallback{

    //데이터전달방법에 setArgument()와 getArgument()도 있음 공부해놓자
    Fragment1 fragment1;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment1 != null){
                    fragment1.onCommandFromActivity("show", "액티비티로부터 전달됨");
                }
            }
        });

        fragment1 = new Fragment1();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    }

    public void onCommand(String command, String data){
        button.setText(data);
    }
}
