package com.example.customlistview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //리스트 뷰를 구성하기 위한 문자열 배열
    String[] data = {
            "데이터1","데이터2","데이터3","데이터4","데이터5","데이터6"
    };

    //뷰의 주소값을 담을 참조변수
    ListView list1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = findViewById(R.id.list1);
        text2 = findViewById(R.id.textView2);
        //어뎁터를 생성한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row, R.id.textView, data);
        list1.setAdapter(adapter);

        //리스너셋팅
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);
    }
    class ListListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            text2.setText(data[position]);
        }
    }
}
