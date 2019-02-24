package com.example.a82107.cinemaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GundoInfoFragment extends Fragment {
    CommentAdapter adapter;

    ImageButton likeButton;
    ImageButton dislikeButton;
    TextView likeCountView;
    TextView dislikeCountView;
    Button showAllButton;
    TextView writeView;
    ListView listView;

    int likeCount;
    int dislikeCount;
    boolean likeState = false;
    boolean dislikeState = false;

    View rootView2;


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프래그먼트 메인을 인플레이트해주고 컨테이너에 붙여달라는 뜻임
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gundo_info_fragment, container, false);
        likeButton = rootView.findViewById(R.id.likeButton);
        dislikeButton = rootView.findViewById(R.id.dislikeButton);
        likeCountView = rootView.findViewById(R.id.likeCountView);
        dislikeCountView = rootView.findViewById(R.id.dislikeCountView);
        showAllButton = rootView.findViewById(R.id.showAll);
        writeView = rootView.findViewById(R.id.writeView);

        //좋아요카운트, 싫어요카운트 초기화
        likeCount = Integer.parseInt(likeCountView.getText().toString());
        dislikeCount = Integer.parseInt(dislikeCountView.getText().toString());

        listView = rootView.findViewById(R.id.listView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem(R.drawable.user1, 5, "Android", "와 이건 대작입니다!!"));

        //리스트뷰에 어뎁터 연결
        listView.setAdapter(adapter);

        //좋아요버튼 클릭리스너
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeState) {
                    decrLikeCount();
                } else {
                    incrLikeCount();
                }

                likeState = !likeState;
            }
        });

        //싫어요버튼 클릭리스너
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dislikeState) {
                    decrDislikeCount();
                } else {
                    incrDislikeCount();
                }

                dislikeState = !dislikeState;
            }
        });

        //모두보기 버튼 리스너
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "모두보기", Toast.LENGTH_SHORT).show();
                //모두보기화면으로 이동
                showViewAllActivity();
            }
        });

        //한줄평 작성하기 온클릭 리스너
        writeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "작성하기", Toast.LENGTH_SHORT).show();
                ;
                //작성하기 화면으로 이동
                showCommentWriteActivity();

            }
        });
        rootView2 = rootView;
        return rootView;
    }

    //좋아요 증가 및 감소 메서드(4개)
    public void incrLikeCount() {
        if (dislikeState) {
            decrDislikeCount();
            dislikeState = false;
        }
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);

    }

    public void decrLikeCount() {
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));
        likeButton.setBackgroundResource(R.drawable.thumbs_up_selecter);
    }

    public void incrDislikeCount() {
        if (likeState == true) {
            decrLikeCount();
            likeState = false;
        }
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDislikeCount() {
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        dislikeButton.setBackgroundResource(R.drawable.thumbs_down_selecter);
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

                view = new CommentItemView(getActivity());
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

    public void showCommentWriteActivity(){
        Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
        startActivityForResult(intent, 101);
    }

    public void showViewAllActivity(){
        Intent intent = new Intent(getActivity(), ViewAllActivity.class);
        //getCount를 for문에 하면 계속 참조하므로 밖에 뺴준다.
        int count = adapter.getCount();
        ArrayList<CommentItem> commentItems = new ArrayList<CommentItem>(count);

        for(int i =0; i <count; i++){
            commentItems.add((CommentItem) adapter.getItem(i));
        }
        //prarcelable을 구현한 객체를 담은 ArrayList 데이터를 보냄
        //객체만 보낼떈 그냥 putExtra로함 =
        //ex) intent.puExtra("ex", new CommentItem(new CommentItem(R.drawable.user1, 5,"JIN210", "정말 재밌고 긴장감이 넘쳤어요!!^^");
        intent.putParcelableArrayListExtra("commentItems", commentItems);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 102);
    }


}




