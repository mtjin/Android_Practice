package com.example.mytwinanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
/*
* 트윈 애니메이션을 위한 액션(Action) 정보는 XML 리소스로 정의하거나 자바 코드에서 직접 객체로 만들 수 있습니다.

애니메이션을 위한 XML 파일은 /res/anim 폴더의 밑에 두어야 하며 확장자를 xml로 해야 합니다.

이렇게 리소스로 포함된 애니메이션 액션 정의는 다른 리소스와 마찬가지로 빌드할 때 컴파일되어 설치 파일에 포함됩니다.

확대/축소를 위한 애니메이션 액션 정보는 <scale> 태그를 사용할 수 있으며 다음과 같이 정의할 수 있습니다.

시작 시간과 지속 시간은 각각 startOffset과 duration으로 정의됩니다.

startOffset은 시작할 시간을 지정하는 것으로 애니메이션이 시작한 지 얼마 후에 이 액션이 수행될 것인지를 알 수 있도록 합니다.

duration은 애니메이션이 지속하는 시간으로 여기에서는 2.5초 동안 지속하도록 되어 있습니다. (시간 단위가 밀리초이므로 2500 밀리초는 2.5초가 됩니다.)

<scale> 태그는 대상을 확대하거나 축소할 때 사용되는데, 크기를 변경하기 위한 축의 정보는 X축과 Y축에 대하여 각각 pivotX와 pivotY로 지정됩니다.

fromXScale과 fromYScale은 시작할 때의 확대/축소비율이며, toXScale과 toYScale은 끝날 때의 확대/축소비율입니다.

여기에서는 1.0으로 시작하여 2.0으로 끝나므로 원래의 크기에서 시작해서 두 배의 크기로 확대되는 애니메이션이 수행되게 됩니다.
* */


/*
*  <scale
        android:startOffset = "1500"  ==>시작하는시간 1.5초후 시작
        android:pivotX="50%"    ==>  애니메이션 할떄 x좌표위치 50%니깐 중앙
        android:pivotY="50%"       ==>마찬가지
        android:fromXScale="1.0"    ==> 가로방향 처음크기 1이니깐 본래크기임.
        android:fromYScale="1.0"
        android:toXScale="0.3"       ==>가로방향 애니메이션실행후 크기 0.3이니깐 원래크기 1에서 0.3배크기로 줄어듬
        android:toYScale="0.3"
        android:duration ="1500"/>    ==>해당애니메이션 끝마치는데 걸리는 동작시간
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale); //우리가 anim폴더에 넣어놨던 액션 정보로딩해서 반환
                v.startAnimation(scale); //받아온 애니베이션 실행
                //실제로 애니메이션은 뷰가 모양이 바뀌는게아니라 그래픽적인 효과를 주는것일 뿐이므로 그래픽효과(애니메이션) 끝나면 원래 모양으로 돌아온다.
            }
        });
    }
}
