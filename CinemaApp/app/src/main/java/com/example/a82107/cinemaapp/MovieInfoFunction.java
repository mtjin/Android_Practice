package com.example.a82107.cinemaapp;

import android.content.Intent;

public interface MovieInfoFunction {


    public void showCommentWriteActivity();

    public void showViewAllActivity();


    public void onActivityResult(int requestCode, int resultCode, Intent intent);
}
