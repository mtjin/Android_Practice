package com.example.mycaptureintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SD카드 접근하는건 manifest에 접근권한을 등록해놔야한다. (그리고 이 권한이 위험권한이므로 gradle에서 타겟sdk를  22로 낮춰야한다.
        File sdcard = Environment.getExternalStorageDirectory(); //SD카드쪽의 폴더를 참조할 수 있다.
        file = new File(sdcard, "capture.jpg");  //sdcard라는 폴더와 파일을 하나 매개변수로 한다.

        imageView = findViewById(R.id.imageView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }

    public void capture() {
        /*
         홈페이지 주소라면 흔히 URL 로 알고있겠지만, 안드로이드에서는 Uri 라는 타입 (정확히는 클래스)를 사용하기도 한다. ( U 만 대문자이고 ri 는 소문자이다. )
        URL 과 Uri 의 차이는
        URL 은 웹 상에서 서비스를 제공하는 파일들의 위치를 표시하기 위한 홈페이지 주소에 해당하는 것이고,

        Uri 는 컨텐트 프로바이더(Content Provider) 의 접근규칙, 즉 컨텐트 프로바이더의 주소이다.
        Uri 는 홈페이지 주소 뿐만 아니라, 파일의 경로 ( 폰으로 치자면, SD 카드 내의 폴더에 접근 할 수도 있다.), 데이터 베이스로의 접근 등을 값으로 가질 수 있다. 즉 Uri 가 URL 을 포함하는 상위 개념인 것이다.
        * */
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); //우리가 단말에서 사진을 찍었을 경우에 어떤 파일로 저장을 한건지 지정가능, 위에서 file 경로와 이름을 지정해놨다. 그 파일의 Uri를 만들어서 인텐트에넣어줌
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            //파일을 그대로 보여줄수도 있으나 요즘은 화질이 좋은 카메라와 사진이 많아서 사진이 몇메가 이상이여서 메모리떔에 앱이 종료되는경우가 있으므로 비트맵 이미지로 변환해준다.
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8; // 8분의1크기로 비트맵 객체를 만들어달라는 옵션
            Bitmap bitMAp = BitmapFactory.decodeFile(file.getAbsolutePath(), options); //파일을 디코딩해서 비트맵객체로 만들어줌,  bitmap에 사진파일이 메모리에 비트맵으로 만들어서 담아짐.
            imageView.setImageBitmap(bitMAp);
        }
    }

}
