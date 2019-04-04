package com.example.mypagesliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
/*
* 페이지 슬라이딩은 버튼 등을 눌렀을 때 보이지 않던 뷰가 슬라이딩 방식으로 보이는 것으로 여러 뷰를 하나씩 전환하면서 보여주는 방식에 애니메이션을 결합한 것입니다.

대표적인 예로, 앱의 [메뉴(MENU)] 버튼을 눌렀을 때 메뉴가 위쪽이나 아래쪽에서 올라오는 기능을 들 수 있습니다.

이렇게 겹쳐져 있는 여러 뷰를 하나씩 전환하면서 보여주기 위한 방법으로 프레임 레이아웃을 사용한다는 것은 이미 배운 내용입니다.
* */
public class MainActivity extends AppCompatActivity {
    LinearLayout page; //메인레이아웃에서 두번쨰 리니어레이아웃
    Animation translateLeft;
    Animation translateRight;
    Button button;
    Boolean isPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translateLeft.setAnimationListener(listener);
        translateRight.setAnimationListener(listener);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    page.startAnimation(translateRight);
                }else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeft);
                }
            }
        });
    }

    //애니메이션 관련 리스너 콜백함수구현가능
    class SlidingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) { //애니메이션이 끝난 후 동작
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);
                button.setText("열기");
                isPageOpen = false;
            }else{
                button.setText("닫기");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
