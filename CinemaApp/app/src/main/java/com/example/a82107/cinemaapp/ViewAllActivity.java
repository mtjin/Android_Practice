package com.example.a82107.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {
    CommentAdapter adapter;
    ArrayList<CommentItem> commentItems;
    //모두보기화면에서 작성하기로 한것들을 담아서 메인화면으로 옮기는데 사용
    ArrayList<CommentItem> intentItems = new ArrayList<CommentItem>();
    TextView writeView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        setTitle("한줄평 목록");

        writeView = findViewById(R.id.writeView);
        listView = findViewById(R.id.listView);

        //메인엑티비티에서 전달된 인텐트가 여기저장됨
        Intent passedIntent = getIntent();
        processIntent(passedIntent);

        writeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "작성하기", Toast.LENGTH_SHORT).show();
                //작성하기 화면으로 이동
                showCommentWriteActivity();

            }
        });
    }

    class CommentAdapter extends BaseAdapter {

        ArrayList<CommentItem> items = new ArrayList();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        public void addItem(CommentItem item) {
            items.add(item);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentItemView view = null;
            if (convertView == null) {
                view = new CommentItemView(getApplicationContext());
            } else {
                view = (CommentItemView) convertView;
            }

            CommentItem item = items.get(position);
            view.setImageView(item.getPhoto());
            view.setRatingBar(item.getRatingScore());
            view.setName(item.getName());
            view.setEvaluation(item.getText());
            return view;

        }
    }

    public void showCommentWriteActivity() {
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivityForResult(intent, 103);
    }

    public void backToMain() {
        Intent intent = new Intent();
        //모두보기화면에서 작성하기했던 데이터들을 메인화면에도 전달
        intent.putParcelableArrayListExtra("intentItems",intentItems);
        setResult(RESULT_OK, intent);
        finish();
    }

    //전달받은 commentItemView 어레이리스트를 띄워줘야함
    private void processIntent(Intent intent) {
        if (intent != null) {
            //paracelable구현한 객체 리스트들 데이터받아옴
            commentItems = intent.getParcelableArrayListExtra("commentItems");

            //어뎁터에 받은 데이터 다넣어줌
            adapter = new CommentAdapter();
            for (int i = 0; i < commentItems.size(); i++) {
                adapter.addItem(commentItems.get(i));
            }
            //리스트뷰에 어뎁터 연결
            listView.setAdapter(adapter);


        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "뒤로가기", Toast.LENGTH_SHORT).show();
        backToMain();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 103){
            if(intent != null){
                float ratingScore = intent.getFloatExtra("ratings", 5);
                String contents = intent.getStringExtra("contents");
                CommentItem commentItem = new CommentItem(R.drawable.user1, ratingScore,"JIN210", contents);

                adapter.addItem(commentItem);

                //어댑터갱신
                adapter.notifyDataSetChanged();

                //작성하기한 데이터 저장(모두보기화면에서 작성하기를 할때마다
                //작성하기화면에서 저장한 데이터들을 저장해줌(메인화면에도 전달해서 갱신하기위해)
                intentItems.add(commentItem);
            }
        }
    }
}
