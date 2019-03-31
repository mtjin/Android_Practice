package com.example.mycapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//카메라권한등록과 타겟 sdk 22로낮춰야함
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    CameraSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView3);
        surfaceView = findViewById(R.id.surfaceView);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }

    public void capture() {
        //버튼을 눌렀을 때 카메라 미리 보기를 위해 만들었던 객체의 capture 메소드를 호출하도록 합니다.
        //
        //그러면 사진을 찍은 결과를 바이트 배열로 받을 수 있습니다.
        surfaceView.capture(new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) { //data가 사진찍은것을 바이트배열로 받은거다.
                BitmapFactory.Options options  =new BitmapFactory.Options();
                options.inSampleSize = 8; //비트맵객체를 1/8크기로 만드는 옵션추가
                Bitmap bitmap= BitmapFactory.decodeByteArray(data , 0 , data.length); //넘어온 바이트 데이터를 가지고 비트맵객체만듬

                imageView.setImageBitmap(bitmap);

                //사진을 찍게되면 미리보기가 중지되므로 다시 미리보기 시작을 시킴.
                camera.startPreview();

            }
        });
    }
}
