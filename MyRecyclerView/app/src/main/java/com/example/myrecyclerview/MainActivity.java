package com.example.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리사이클러뷰는 리스트뷰처럼 단순 껍데기일뿐이다. 어댑터를 만들어 관리 설정해줘야한다.
        recyclerView = findViewById(R.id.recyclerView);

        //inearLayoutManager.HORIZONTAL로 넘기는 방향설정 가능 (첫번쨰파라미터는 context 세번쨰파라미터는 아이템이 보이는 방향을 애기한다.)
        //세번쨰파라미터는 예를들어 채팅방같은 경우 메세지가 아래에서 위로 올라가는 방향같은거 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); //레이아웃매니저 생성

        recyclerView.setLayoutManager(layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌

        adapter = new SingerAdapter(getApplicationContext());
        //아이템추가
        adapter.addItem(new SingerItem("조용필", "010-1111-2222"));
        adapter.addItem(new SingerItem("디셈버", "010-3333-4444"));
        adapter.addItem(new SingerItem("마마무", "010-5555-3333"));
        adapter.addItem(new SingerItem("박효신", "010-4444-8888"));
        adapter.addItem(new SingerItem("태진아", "010-0000-9999"));

        //어댑터에 연결
        recyclerView.setAdapter(adapter);

        //어댑터클래스에 직접 이벤트처리관련 코드를 작성해줘야함 (리스트뷰처럼 구현되어있지않음 직접 정의해놔야한다.)
        //setOnItemClickListener라는 이름으로 이벤트 메소드 직접 정의한거임
        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerItem item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "해당 가수가 선택됨==> " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
