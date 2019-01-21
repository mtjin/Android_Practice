package com.example.listview_adapter_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingleAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        ListView listView = findViewById(R.id.listView);

        adapter = new SingleAdapter();
        adapter.addItem(new SingerItem("트와이스", "010-2223-1324", R.drawable.a));
        adapter.addItem(new SingerItem("걸스데이", "010-2223-2222", R.drawable.b));
        adapter.addItem(new SingerItem("소녀시대", "010-2223-1333", R.drawable.c));
        adapter.addItem(new SingerItem("와우", "010-2223-1312", R.drawable.z));
        adapter.addItem(new SingerItem("레드벨벳", "010-2223-4141", R.drawable.e));
        adapter.addItem(new SingerItem("블랙핑크", "010-2223-2334", R.drawable.f));
        adapter.addItem(new SingerItem("티아라", "010-2223-7789", R.drawable.g));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(name, mobile, R.drawable.b));

                //어탭터 쪽에서 리스트뷰쪽으로 갱신하라고 알려줌
                adapter.notifyDataSetChanged();
            }
        });
    }

    class SingleAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //SingerItemView view  =new SingerItemView(getApplicationContext());

            //위 주석보다 아래처럼 생성을많이함(메모리 효율성을 위해서)
            SingerItemView view = null;
            if (convertView == null) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());
            return view;
        }
    }
}
