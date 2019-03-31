package com.example.myaudioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

//인터넷권한필요
public class MainActivity extends AppCompatActivity {
    //스마트폰에서 저장하면 확장자가 기본이 amr 로 보통 저장이된다.
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeAudio();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });
    }

    public void playAudio(){
        try {
            //여러번 누르면 new MediaPlayer가 여러개 만들어져서 문제가 생길것이다.  그래서 항상
            closePlayer(); //closePlayer를 항상 처음에 확인을 해주도록 한다.
            player = new MediaPlayer();
            player.setDataSource(url);//웹(인터넷)에있는걸 가져와서 플레이를 하게됨

            player.prepare(); //시작하기전에 준비하는 과정이필요함.
            player.start();

            Toast.makeText(this, "재생시작됨", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseAudio(){
        if(player != null){ //플레이어가 널이면 실행중이지 않는거임. (null이 아니여도 플레이할게 있다는거지 플레이중이라는건 모름)
            position = player.getCurrentPosition(); //어디까지 진행했다를 position에 저장해놈
            player.pause(); // 일시정지
            Toast.makeText(this, "일시정지됨", Toast.LENGTH_LONG).show();
        }
    }

    public void resumeAudio(){
        if(player != null && !player.isPlaying()){ //isPLaying은 재생되고있는지 유무
            player.seekTo(position);  //시작되는 시점을 잡아준다.
            player.start();
            Toast.makeText(this, "재시작됨", Toast.LENGTH_LONG).show();
        }
    }

    public void stopAudio(){
        if(player != null && player.isPlaying()){ //isPLaying은 재생되고있는지 유무
            player.stop();
            Toast.makeText(this, "중지됨", Toast.LENGTH_LONG).show();
        }
    }

    //오디오(스피커)나 마이크같은거는 자원이 한정되있다. 그래서 누군가 이것을 잡고있으면(lock이라고함) 다른앱이나 이런데서 사용할수가 없다.
    //그래서 이런 오디오나 마이크같은거를 사용할려면 리소스를 해제해주는 과정이 필요하다.
    public void closePlayer(){//kill
        if(player !=null){
            player.release();
            player = null;
        }
    }
}
