package com.example.mysummary7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.chrisbanes.photoview.PhotoView;

//https://github.com/chrisbanes/PhotoView 라이브러리 가져와서씀
public class MainActivity extends AppCompatActivity {
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();

                //url로 되있으니깐(http,https) 인텐트가 웹브라우저를 띄워줄것이다. (전화걸기는 tel: 로 알듯이)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.image4);
    }
}
