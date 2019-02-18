package com.example.a82107.cinemaapp;

import android.content.Intent;

public interface MovieInfoFunction {
    //좋아요 증가 및 감소 메서드(4개)
    public void incrLikeCount();

    public void decrLikeCount();

    public void incrDislikeCount();

    public void decrDislikeCount();

    public void showCommentWriteActivity();

    public void showViewAllActivity();


    public void onActivityResult(int requestCode, int resultCode, Intent intent);
}
