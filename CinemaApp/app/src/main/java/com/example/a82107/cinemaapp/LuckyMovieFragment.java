package com.example.a82107.cinemaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LuckyMovieFragment extends Fragment {
    public LuckyMovieFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.lucky_movie_fragment, container, false);
        return rootView;
        //xml 레이아웃이 인플레이트 되고 자바소스 코드와 연결이된다.
    }
}
