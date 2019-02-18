package com.example.a82107.cinemaapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentItemView extends LinearLayout {
    CircleImageView imageView;
    RatingBar ratingBar;
    TextView textView;
    TextView textView2;

    public CommentItemView(Context context) {
        super(context);
        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        //리스트뷰에서 커스텀 뷰를 만드려면 반드시 행 레이아웃을 정의해야합니다.
        // LayoutInflater는 xml을 만들고 어댑터의 코드를 짤때 보통 사용
        //XML에 정의된 Resource(자원) 들을 View의 형태로 반환해 줍니다.
       //보통 Activity를 만들면 onCreate() 메서드에 기본으로 추가되는 setContentView(R.layout.activity_main) 메서드와 같은 원리
        //이 클래스는 setContentView가 따로없으므로 해줘야함
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this,true);
        imageView = findViewById(R.id.imageView);
        ratingBar = findViewById(R.id.ratingBar);
        textView = findViewById(R.id.textView_id);
        textView2 = findViewById(R.id.textView_oneLine);
    }

    public void setImageView(int photo){
        imageView.setImageResource(photo);
    }

    public void setRatingBar(float ratingScore) {
        ratingBar.setRating(ratingScore);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setEvaluation(String str){
        textView2.setText(str);
    }

}
