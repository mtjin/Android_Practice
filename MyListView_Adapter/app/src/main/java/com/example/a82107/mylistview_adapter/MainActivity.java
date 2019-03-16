package com.example.a82107.mylistview_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        ListView listView = findViewById(R.id.listView);
        adapter = new SingerAdapter();
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

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //SingerItemView view  =new SingerItemView(getApplicationContext());

            //위에 대로하면 1000개의 아이템이 다 로딩되면 메모리가 엄청나게 쓰일거다.(뷰객체가 너무많이 만들어주면 메모리를 엄청 소비할 수 있다.)
            //화면에 보이는게 열몇개밖에 안된다고하면 열몇개보다 조금더 많은 수를 만든다음에
            //화면에 보여줬다가 안보여지게 되는 것들은 다시 재사용을 하는거다. (즉 뷰는 그대로고 데이터만 싹싹 바꿔주는것이다.)
            //매개변수에서 convertView라는 것을 이용해 코드를 재사용할 수 있다
            //실제 화면에 그려지는 아이템을 ConvertView 라는 배열로 관리하는데, 화면에 보여지는 만큼 Convert View를 생성하고 스크롤시 View를 재활용하기 때문에 성능면으로 우수한 구조
            //실무에서 위 주석보다 아래처럼 생성을많이함(메모리 효율성을 위해서)
            SingerItemView view = null;
            if (convertView == null) {  //convertView가 null인 경우에만 뷰를 생성해줌( 즉 화면에 띄워질 개수의 리스트뷰 개수만큼 뷰가 만들어지지 않았고 그러므로 아직 재활용할떄가 아니다.) (그러므로 뷰를 생성해준다.)
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;  //여기 else문에서의 converView는 이전에 썻던 뷰를 말함.(캐스팅하여 사용했던 뷰를 재사용해준다. 생성X 썻던뷰만 가져와서 밑 코드에서 내용만 바꿔준다 생각)
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResid());
            return view;
        }
    }
}
