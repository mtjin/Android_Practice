package com.example.a82107.cinemaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GundoInfoFragment extends Fragment {
    //이 프래그먼트가 꼭 MainActivity말고 다른 액티비티에서도 사용될 수 있으니깐 인터페이스를 이용하는 것이다.
    FragmentInfoCall callback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentInfoCall) {
            callback = (FragmentInfoCall) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //이건 꼭 필수는 아니지만 개념적으로보면 해주는게 좋음
        if (callback != null) {
            callback = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gundo_info_fragment, container, false);

        return rootView;

    }
}
