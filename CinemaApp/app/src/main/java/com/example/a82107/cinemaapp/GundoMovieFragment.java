package com.example.a82107.cinemaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GundoMovieFragment extends Fragment {

    FragmentInfoCall movieInfo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentInfoCall){
            movieInfo = (FragmentInfoCall) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(movieInfo != null){
            movieInfo = null;
        }
    }

    public GundoMovieFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //xml 레이아웃이 인플레이트 되고 자바소스 코드와 연결이된다.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gundo_movie_fragment, container, false);

        //영화프래그먼트의 버튼
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movieInfo != null){
                    movieInfo.callMovieInfo("gundo", "군도프래그먼트에서 전달함");
                }
            }
        });
        return rootView;

    }
}
