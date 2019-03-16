package com.example.a82107.mylistview_adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    ImageView imageView;
    public SingerItemView(Context context) {
        super(context);
        init(context);//이 클래스 생성할때 밑에 singer_item.xml 인플레이트 코드작업해준거 실행해줌으로써
        //인플레이트된 상태이고 xml조작이 가능해짐 (MainActivity에서 이 뷰를 생성하고 xml 이미지뷰나 텍스트뷰에 내용을 삽입한다거나 할수 있어짐.
    }

    public SingerItemView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context); //이 클래스 생성할때 밑에 singer_item.xml 인플레이트 코드작업해준거 실행해줌으로써
                        //인플레이트된 상태이고 xml조작이 가능해짐 (MainActivity에서 이 뷰를 생성하고 xml 이미지뷰나 텍스트뷰에 내용을 삽입한다거나 할수 있어짐.
    }

    //singer_item.xml을 인플레이트해서 받아서 코드작업함
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public  void setMobile(String mobile){
        textView2.setText(mobile);
    }

    public  void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
