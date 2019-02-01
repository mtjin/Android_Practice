package com.example.a82107.mysummary3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView outputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        outputView = findViewById(R.id.outputView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentWriteActivity();
            }
        }));
    }

    public void showCommentWriteActivity(){
        float rating = ratingBar.getRating();
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra("rating",rating);
        //응답을 받을거니깐 ForResult로함
        startActivityForResult(intent, 101);
    }

    //응답받을떄 사용하는 메소드

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                String contents = intent.getStringExtra("contents");
                outputView.setText(contents);
            }
        }
    }
}
