package com.example.myaudiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

//sd카드 접근, 녹음 접근구너한 필요
public class MainActivity extends AppCompatActivity {
    //프로젝트의 res/raw 폴더에 a.mp3 라는 이름으로 음악 파일을 넣어두었다면 다음과 같이 지정할 수 있습니다.
    //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.a);
    //단말 SD 카드에 넣어둔 파일의 폴더 위치가 /sdcard/a.mp3라면 다음과 같이 지정할 수 있습니다.
    //String filepath = "/sdcard/a.mp3";

    //스마트폰에서 저장하면 확장자가 기본이 amr 로 보통 저장이된다.
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaRecorder recorder; //녹음관련객체
    String filename;

    MediaPlayer player; //미디어플레이 관련객체
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File sdcard = Environment.getExternalStorageDirectory(); //sd카드를 참조(해당 저장소의 폴더를 참조한거임)
        File file = new File(sdcard, "recorded.mp4");//sdcard경로에 recorded라는 이름의 파일
        filename = file.getAbsolutePath(); //경로설정
        Log.d("MaindActivity", "저장할 파일명:  " + filename);

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

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordAudio();
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
    }

    public void playAudio() {
        try {
            //여러번 누르면 new MediaPlayer가 여러개 만들어져서 문제가 생길것이다.  그래서 항상
            closePlayer(); //closePlayer를 항상 처음에 확인을 해주도록 한다.
            player = new MediaPlayer();
            //player.setDataSource(url);//웹(인터넷)에있는걸 가져와서 플레이를 하게됨
            player.setDataSource(filename); //예전에 재생 프로젝트에서는 웹에서가져왔지만 이번에는 파일에서 가져오는걸로 바꿔봄
            player.prepare(); //시작하기전에 준비하는 과정이필요함.
            player.start();

            Toast.makeText(this, "재생시작됨", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseAudio() {
        if (player != null) { //플레이어가 널이면 실행중이지 않는거임. (null이 아니여도 플레이할게 있다는거지 플레이중이라는건 모름)
            position = player.getCurrentPosition(); //어디까지 진행했다를 position에 저장해놈
            player.pause(); // 일시정지
            Toast.makeText(this, "일시정지됨", Toast.LENGTH_LONG).show();
        }
    }

    public void resumeAudio() {
        if (player != null && !player.isPlaying()) { //isPLaying은 재생되고있는지 유무
            player.seekTo(position);  //시작되는 시점을 잡아준다.
            player.start();
            Toast.makeText(this, "재시작됨", Toast.LENGTH_LONG).show();
        }
    }

    public void stopAudio() {
        if (player != null && player.isPlaying()) { //isPLaying은 재생되고있는지 유무
            player.stop();
            Toast.makeText(this, "중지됨", Toast.LENGTH_LONG).show();
        }
    }

    //오디오(스피커)나 마이크같은거는 자원이 한정되있다. 그래서 누군가 이것을 잡고있으면(lock이라고함) 다른앱이나 이런데서 사용할수가 없다.
    //그래서 이런 오디오나 마이크같은거를 사용할려면 리소스를 해제해주는 과정이 필요하다.
    public void closePlayer() {//kill
        if (player != null) {
            player.release();
            player = null;
        }
    }

    public void recordAudio() {
        recorder = new MediaRecorder();

        //인터넷에서 소리를 녹음을하든 마이크에서 녹음을 하든 여러 가지가있을텐데 마이크로 받겠다고 세팅
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //압축포맷이나 이런것들 지정하는거라 생각
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//인코더: 소리(바이트)를 처리해주는 기본적인 클래스

        recorder.setOutputFile(filename); //처음 위에서 설정한 filename경로로 출력저장을 설정 할 수 있다.

        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(this, "녹음 시작됨", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopRecording() {
        if(recorder != null){
            recorder.stop();
            recorder.release();
            recorder = null;
            Toast.makeText(this, "녹음 중지됨", Toast.LENGTH_LONG).show();
        }
    }
}