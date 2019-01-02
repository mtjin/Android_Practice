package com.example.twolinelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // 리스트의 항목을 구성하기 위한 문자열 배열
    String [] data1 = {
            "문자열1", "문자열2", "문자열3", "문자열4", "문자열5", "문자열6"
    };
    String [] data2 = {
            "String1", "String2", "String3", "String4", "String5", "String6"
    };
    // 뷰의 주소 값을 담을 참조변수
    ListView list1;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 뷰의 주소 값을 담는다.
        list1 = (ListView)findViewById(R.id.list1);
        text1 = (TextView)findViewById(R.id.textView);
        // 데이터를 담을 ArrayList
        ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();
        // ArrayList에 데이터를 담는다.
        for(int i = 0 ; i < data1.length ; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("str1", data1[i]);
            map.put("str2", data2[i]);

            data_list.add(map);
        }
        String [] keys = {"str1", "str2"};
        int [] ids = {android.R.id.text1, android.R.id.text2};
        // 어뎁터를 리스트뷰에 셋팅한다.
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, android.R.layout.simple_list_item_2, keys, ids );
        list1.setAdapter(adapter);

        // 리스너를 셋팅한다.
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);
    }

    class ListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            text1.setText(data1[position]);
        }
    }
}








