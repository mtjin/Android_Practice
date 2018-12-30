package com.example.listviewbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //뷰의 주소 값을 담을 참조변수
    ListView list1;
     TextView text1;
    //리스트뷰 구성을 위해 필요한 문자열 배열
    String[] data1 = {
            "리스트1","리스트2","리스트3","리스트4","리스트5",
            "리스트6","리스트7","리스트8","리스트9","리스트10",
            "리스트11","리스트12","리스트13","리스트14","리스트15"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰의 주소값을 얻어온다.
        list1 = findViewById(R.id.list1);
        text1 = findViewById(R.id.textView2);
        //몇 개의 항목을 보여줄 것인가?
        //각 항목에 어떤 데이터를 보여줄 것인가?
        //각 항목은 어떤 모양으로 보여줄 것인가?
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
        //리스트뷰에 어뎁터를 설정한다.
        list1.setAdapter(adapter);

        //리스너를 설정한다.
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);
    }

    class ListListener implements AdapterView.OnItemClickListener{

        @Override
        //항목을 터치하면 호출되는 메소드
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            text1.setText(data1[position]);
        }
    }
}
