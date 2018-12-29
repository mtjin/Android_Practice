package com.example.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton radio6, radio7;
    RadioGroup group1, group2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰의 주소값을 얻어온다.
        radio6 = findViewById(R.id.radioButton6);
        radio7 = findViewById(R.id.radioButton7);
        group1 = findViewById(R.id.group1);
        group2 = findViewById(R.id.group2);
        text1 = findViewById(R.id.textView3);
        text2 = findViewById(R.id.textView4);

        //라디오그룹에 리스너를 설정한다.
        RadioListener listener= new RadioListener();
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);
    }

    public void btn1Method(View view){
        radio6.setChecked(true);
        radio7.setChecked(true);
    }

    public void btn2Method(View view){
        //각 라디오 그룹 내에서 선택되어 있는 라이도 버튼의 id값을 가져온다
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();

        switch(id1){
            case R.id.radioButton4:
                text1.setText("1-1 라디오 버튼이 선택되었습니다");
                break;
            case R.id.radioButton5:
                text1.setText("1-2 라디오 버튼이 선택되었습니다.");
                break;
            case R.id.radioButton6:
                text1.setText("1-3 라디오 버튼이 선택되었습니다.");
                break;
        }

        switch(id2){
            case R.id.radioButton7:
                text2.setText("2-1 라디오 버튼이 선택되었습니다.");
                break;
            case R.id.radioButton8:
                text2.setText("2-2 라디오 버튼이 선택되었습니다.");
                break;
            case R.id.radioButton9:
                text2.setText("2-3 라디오 버튼이 선택되었습니다.");
                break;
        }
    }

    class RadioListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //체크 상태가 변경된 라디오 그룹의 아이디를 추출한다.
            int id = group.getId();

            switch(id){
                case R.id.group1:
                    switch (checkedId){
                        case R.id.radioButton4:
                            text1.setText("1-1 라디오 버튼이 선택되었습니다");
                            break;
                        case R.id.radioButton5:
                            text1.setText("1-2 라디오 버튼이 선택되었습니다.");
                            break;
                        case R.id.radioButton6:
                            text1.setText("1-3 라디오 버튼이 선택되었습니다.");
                            break;
                    }
                    break;
                case R.id.group2:
                    switch (checkedId){
                        case R.id.radioButton7:
                            text2.setText("2-1 라디오 버튼이 선택되었습니다.");
                            break;
                        case R.id.radioButton8:
                            text2.setText("2-2 라디오 버튼이 선택되었습니다.");
                            break;
                        case R.id.radioButton9:
                            text2.setText("2-3 라디오 버튼이 선택되었습니다.");
                            break;
                    }
                    break;
            }
        }
    }
}
