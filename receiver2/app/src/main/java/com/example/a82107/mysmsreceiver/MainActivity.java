package com.example.a82107.mysmsreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //그냥 앱생성용(실질적으로 하는거없음)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RECEIVE_SMS 사용권한이 주어져있는지 체크함
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "SMS 수신 권한 주어져있음.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "SMS 수신 권한 없음.",Toast.LENGTH_LONG).show();

           // if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
           //     Toast.makeText(this, "SMS 권한 설명 필요함.",Toast.LENGTH_LONG).show();
          //  }else{
                //RECEIVED_SMS라고 하는 권한을 부여해달라 요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
           // }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    //사용자가 수락한 경우 0번쨰에 수락했는지안했는지 결과값 들어있음
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 승인함.",Toast.LENGTH_LONG).show();
                    }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 거부함.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "SMS 수신 권한을 부여받지 못함",Toast.LENGTH_LONG).show();
                }
        }
    }
}
