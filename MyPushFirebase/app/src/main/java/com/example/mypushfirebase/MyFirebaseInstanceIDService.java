package com.example.mypushfirebase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.renderscript.Script;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();


        Log.d(TAG, "onTokenRefresh() 호출됨");

    }
}
