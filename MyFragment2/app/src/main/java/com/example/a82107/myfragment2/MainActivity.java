package com.example.a82107.myfragment2;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListFragment fragment1;
    ViewerFragment fragment2;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트매니저
        //소스코드에서 프래그먼트를 추가하고싶다면 이 프래그먼트 매니저를 사용해야함
        manager = getSupportFragmentManager();

        //프래그먼트는 엑티비티와다르게 findViewById가 아니라
        //프래그먼트매니저를통해 이런식으로 받는다
        fragment1 = (ListFragment)manager.findFragmentById(R.id.listFragment);
        fragment2 = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);

        //이번거는 xml로 fragment를 엑티비티위에 올려놨다.
    }

    //ListFragment에서 이 메소드를 호출해서 엑티비티에서 ViewerFragment의 setImage메소드를 호출해서 동작
    //프래그먼트끼리 소통이 불가하므로 엑티비티를 거쳐서 이런식으로 동작하게만듬
    //하나의 프래그먼트에서 다른 프래그먼트로 직접 접근할 수 없으므로 시스템 역할을 하는 액티비티를 통해 명령이나 데이터를 전달해야 합니다.
    //
    public  void onImageChange(int index){
        fragment2.setImage(index);
    }
}
