package com.example.a82107.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    //서비스가 만들어졌다
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() 호출됨");
    }

    //서비스가 없어졌다.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() 호출됨");
    }

    //메인엑티비티클래스에서 전달한 인텐트 객체는 onStartCommand()에서 받아서 처리할 수 있다.
    //서비스가 한번 만들어지면 계속 실행되있는 특성 떄문에 이 메소드에서 처리하는거다.
    //그래서 실행하면 onStart는 버튼을 눌렀을떄 처음만 뜨고 안뜬다 onStartCommand는 버튼을눌러 실행할떄마다 뜬다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨");
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "전달받은 데이터 : " + command + ", " + name);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        //화면이 없는 서비스에서 화면이 있는 액티비티를 띄울 때는 태스크(Task)를 새로 만들어서 연결해야 합니다.
        //이 때문에 FLAG_ACTIVITY_NEW_TASK 플래그를 추가해주게 되는데 일반적인 경우 세 개의 플래그를 같이 사용합니다.
        //NEW_TASK는 화면이 없는데서 화면이 있는 걸 띄울수 있게된다.
        //SINGLE_TOP은 만들어져있는 엑티비티면 그걸 재활용 해달라는말
        //CLEAR_TOP은 그 위에 다른화면이 있으면 그걸 제거해주라
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
