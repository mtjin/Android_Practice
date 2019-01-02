package com.example.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    ViewPager pager1;
    TextView text1;
    // ViewPager를 통해서 보여줄 View 객체를 가지고 있는 ArrayList
    ArrayList<View> viewList = new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 받는다.
        pager1 = (ViewPager)findViewById(R.id.pager);
        text1 = (TextView)findViewById(R.id.textView);

        // View 객체를 생성하여 ArrayList에 담는다.
        LayoutInflater inflater = getLayoutInflater();
        View v1 = inflater.inflate(R.layout.view1, null);
        View v2 = inflater.inflate(R.layout.view2, null);
        View v3 = inflater.inflate(R.layout.view3, null);

        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);

        CustomAdapter adapter = new CustomAdapter();
        pager1.setAdapter(adapter);

        PagerListener listener = new PagerListener();
        pager1.setOnPageChangeListener(listener);
    }

    class CustomAdapter extends PagerAdapter{

        @Override
        // 뷰 페이저를 통해서 보여줄 항목의 개수
        public int getCount() {
            return viewList.size();
        }
        // 뷰 페이저를 통해 보여줄 뷰 객체를 반환한다.
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // position 번째 뷰 객체를 뷰 페이저에 셋팅한다.
            pager1.addView(viewList.get(position));
            return viewList.get(position);
        }
        // instantiateItem 메서드에서 반환한 뷰 객체를 사용할 것인가를 결정하는 메서드
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
        // 뷰가 제거될 때 호출되는 메서드

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            pager1.removeView((View)object);
        }
    }

    class PagerListener implements ViewPager.OnPageChangeListener{
        @Override
        // View의 전환이 완료되었을 때 호출되는 메서드
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            text1.setText(position + "번 째 뷰가 나타났습니다");
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {

        }
    }
}









