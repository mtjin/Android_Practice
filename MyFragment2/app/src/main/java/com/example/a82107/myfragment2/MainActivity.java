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

        manager = getSupportFragmentManager();

        //프래그먼트는 엑티비티와다르게 findViewById가 아니라
        //프래그먼트매니저를통해 이런식으로 받는다
        fragment1 = (ListFragment)manager.findFragmentById(R.id.listFragment);
        fragment2 = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);

        //이번거는 xml로 fragment를 엑티비티위에 올려놨다.
    }

    
    public  void onImageChange(int index){
        fragment2.setImage(index);
    }
}
