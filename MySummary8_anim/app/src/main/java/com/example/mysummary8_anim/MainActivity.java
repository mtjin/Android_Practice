package com.example.mysummary8_anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateUp;
    Animation translateDown;

    LinearLayout menuContainer;

    boolean isShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translateUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_up);
        translateDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_down);

        translateUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                menuContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        menuContainer = findViewById(R.id.menuContainer);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShown){
                    menuContainer.startAnimation(translateUp); //이 애니메이션이 끝난후 컨테이너가 안보이게만들어줘야함(리스너를이용해서)
                    //바로 밑에다 코드를 적으면 애니메이션이 시작하자마자 메뉴컨테이너가 안보이게되어 의도대로안됨.
                }else{
                    menuContainer.setVisibility(View.VISIBLE);
                    menuContainer.startAnimation(translateDown);
                }
                isShown = !isShown;
            }
        });
    }
}
