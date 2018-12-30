package com.example.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조변수
    EditText edit1;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText)findViewById(R.id.editText2);
        text1 = (TextView)findViewById(R.id.textView);

        EnterListener listener = new EnterListener();
        edit1.setOnEditorActionListener(listener);

        WatcherClass watcher = new WatcherClass();
        edit1.addTextChangedListener(watcher);
    }

    public void btn1Method(View view){
        edit1.setText("새롭게 설정한 문자열");
    }

    public void btn2Method(View view){
        String str = edit1.getText().toString();
        text1.setText("입력한 문자열 : " + str);
    }

    class EnterListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            String str = v.getText().toString();

            text1.setText("입력한 문자열 : " + str);

            return false;
        }
    }

    class WatcherClass implements TextWatcher{
        @Override
        // 문자열 값이 변경되었을 때
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            text1.setText("문자열 변경 중 : " + s);
        }
        // 문자열 값이 변경되기 전
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        // 문자열 값이 변경된 이후
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}




