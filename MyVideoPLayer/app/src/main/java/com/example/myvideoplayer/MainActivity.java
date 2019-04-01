package com.example.myvideoplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView2);

        // 실행해보면 알겠지만 동영상을 클릭하면 밑에 뒤로가기,정지(시작),앞으로가기 , 몇초대인지 빨간색 게이지 이런게 뜨는데 이것이 미디어 컨트롤러를 해줬기 떄문에 나타나는거다.
        MediaController controller = new MediaController(this);  //컨트롤러가 필요함
        videoView.setMediaController(controller); //컨트롤러 세팅해줌.
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();//이 파일에 대한 정보를 일부 갖고온다.

        //실행시킬려는 파일이 있고 사용할수 있게 준비를 다 마치면 호출됨
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 준비됨",Toast.LENGTH_LONG).show();
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오디오 볼륨설정 세팅해줄수있다.(오디오 매니저 이용)
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE); //오디오 매니저 시스템 서비스 객체 참조
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //최대크기소리 변수에저장
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, audioManager.FLAG_SHOW_UI); //소리의 크기 설정(볼륨조절) 가능해짐

                videoView.seekTo(0); //처음위초 (0)으로 돌아감
                videoView.start();//동영상 플레이
            }
        });
    }
}
