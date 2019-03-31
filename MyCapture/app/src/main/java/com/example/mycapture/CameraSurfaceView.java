package com.example.mycapture;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

//서피스뷰를 이용해서 카메라 미리보기를 위한 뷰를 정의함 (서피스뷰 상속), SurfaceHolder.Callback IMPLEMENTS
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        holder = getHolder(); //서피스홀더객체 참조
        holder.addCallback(this);
    }

    //SurfaceView 객체가 메모리에 만들어질 때 자동으로 호출되는 surfaceCreated 메소드 안에서는 카메라 객체를 오픈하고 카메라 객체에 서피스홀더 객체를 설정합니다
    @Override
    public void surfaceCreated(SurfaceHolder holder) {//서피스뷰가 메모리에 만들어질때 호출됨
        camera = Camera.open(); //카메라객체참조
        try {
            camera.setPreviewDisplay(holder); //camera 객체에 이 서피스뷰를 미리보기로 쓸 것이다라고 설정함.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //뷰의 크기가 변경되는 시점에는 미리 보기 화면이 보이도록 만들어줍니다.
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { //서피스뷰가 변경되는시점 호출됨
        //화면에 보여지기전에 크기가 결정되는 시점
        camera.startPreview(); //미리보기화면에 픽셀을 막 뿌리기시작 (렌즈로부터 들어온 영상을 뿌려주는거다.)
    }

    //뷰가 메모리에서 사라지는 시점에는 카메라 참조를 해제합니다
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { // 없어질때 호출됨
        camera.stopPreview(); //미리보기중지 ( 미리보기가 많은 리소스를 잡아먹기때문)
        camera.release(); //중지 후 릴리즈해서 리소스가 완전히 없어지게만듬
        camera = null;
    }

    public boolean capture(Camera.PictureCallback callback){ //서피스뷰에서 사진을 찍도록 하는 메소드
        if(camera != null){
            camera.takePicture(null,null, callback);
            return true;
        }else{
            return false;
        }
    }

    //위 세개 매소드 모두 콜백함수임
}
