package com.example.a82107.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class CommentWriteActivity extends AppCompatActivity {
    EditText contentsInput;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);
        setTitle("한줄평 작성");
        ratingBar = findViewById(R.id.ratingBar);
        contentsInput = findViewById(R.id.contentsInput);

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelToMain();
            }
        });
    }

    public void returnToMain(){
        String contents = contentsInput.getText().toString();
        Intent intent = new Intent();
        float ratingScore = ratingBar.getRating();
        //레이팅바 점수 put
        intent.putExtra("ratings", ratingScore);
        //한줄평 put
        intent.putExtra("contents", contents);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelToMain(){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
