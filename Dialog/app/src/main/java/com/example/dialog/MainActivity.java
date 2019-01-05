package com.example.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView3);
    }

    public void btn1Method(View view){
        //다이얼로글에 관한 정보를 관리하는 객체를 생성한다.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //제목
        builder.setTitle("기본 다이얼로그");
        //본문내용
        builder.setMessage("다이얼로그의 본문입니다.");
        //아이콘
        builder.setIcon((R.mipmap.ic_launcher));

        //다이얼로그의 버튼을 셋팅한다.
        Dialog1Listener listener = new Dialog1Listener();

        builder.setPositiveButton("Positive", listener);
        builder.setNeutralButton("Neutral", listener);
        builder.setNegativeButton("Negative", listener);

        //다이얼로그를 띄운다.
        builder.show();
    }
    //다이얼로그의 리스너
    class Dialog1Listener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            //버튼 종류 값으로 분기한다.
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    text1.setText("Positive");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    text1.setText("Neutral");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    text1.setText("Negative");
                    break;
            }
        }
    }

    public void btn2Method(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("커스텀 다이얼로그");
        builder.setIcon(R.mipmap.ic_launcher);

        //다디얼로그를 통해 보여줄 뷰를 생성한다.
        LayoutInflater inflater = getLayoutInflater();
        View v1= inflater.inflate(R.layout.dialog, null);
        //생성한 뷰를 다이얼로그에 셋팅한다.
        builder.setView(v1);

        Dialog2Listener listener = new Dialog2Listener();
        builder.setPositiveButton("확인",listener);
        builder.setNegativeButton("취소", null);

        builder.show();
    }

    class Dialog2Listener implements  DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialog로 형변환한다.
            AlertDialog alert = (AlertDialog)dialog;
            //AlerttDialog가 가지고있는 뷰를 추출한다.
            EditText edit1 = (EditText)alert.findViewById(R.id.editText);
            EditText edit2 = (EditText)alert.findViewById(R.id.editText2);

            //사용자가 입력한 문자열을 가져온다.
            String str1 = edit1.getText().toString();
            String str2 = edit2.getText().toString();

            text1.setText("edit1 : "+ str1 + "\n");
            text1.append("edit2 : " + str2);
        }
    }

    public void btn3Method(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Dialog3Listener listener = new Dialog3Listener();

        DatePickerDialog picker = new DatePickerDialog(this, listener, year, month, day);
        picker.show();
    }

    class Dialog3Listener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            text1.setText(year + "년" + (month + 1) + "월" + dayOfMonth + "일");
        }
    }

    public void btn4Method(View view){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Dialog4Listener listener = new Dialog4Listener();

        TimePickerDialog picker = new TimePickerDialog(this, listener, hour, minute, true);
        picker.show();
    }
    class Dialog4Listener implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            text1.setText(hourOfDay + "시 " + minute + "분");
        }
    }
    public void btn5Method(View view){
        //프로그래스다이얼로그를 생성한다.
        pro = ProgressDialog.show(this, "제목입니다", "본문메시지입니다");

        Handler handler = new Handler();
        ThreadClass thread = new ThreadClass();
        handler.postDelayed(thread, 5000);
    }

    class ThreadClass extends Thread{
        public void run(){
            //,현재 실행중인 프로그래스다이얼로그를 제거한다.
            pro.cancel();
        }
    }
}
