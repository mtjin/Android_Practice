package com.example.a82107.mysmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

//브로트캐스트를 상속받음
public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceive";
    //파싱
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Onreceive() 호출됨");

        //Bundle로 받을 수 있다
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        //한개라도 메세지가 있을 경우
        if (messages.length > 0) {
            //발신번호얻어옴
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "sender : " + sender);
            //내용을가져옴
            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "contents : " + contents);
            //발신 시간을 가져옴
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "received date : " + receivedDate);

            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        //getApplicationContext는 onReceice에서만 사용할 수 있으므로 onReceive에서 context를 받아와서 인텐트에다가 쓴다.
        Intent intent = new Intent(context, SMSActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
        );
        //인텐트에 데이터전달
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        //포맷대로 데이터 가져옴
        intent.putExtra("receivedDate", format.format(receivedDate));
        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];
        for (int i = 0; i < objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
