package com.example.mypushfirebase;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyMS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived() 호출됨");

        String from = remoteMessage.getFrom(); //어디서 데이터왔는지
        Map<String, String> data = remoteMessage.getData();
        String contents = data.get("contents");

        Log.d(TAG, "from : " + from + ", contents : " + contents);

        sendToActivity(getApplication(), from, contents);
    }

    public void sendToActivity(Context context, String from, String contents) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | intent.FLAG_ACTIVITY_CLEAR_TOP); //화면없는곳에서 띄워주는거니깐 NEW_TASK해줘야( 보통 이 세가지는 같이 등록함)
        context.startActivity(intent);
    }
}
