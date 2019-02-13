package com.example.a82107.mydrawer;

import android.os.Bundle;

public interface FragmentCallback {
    //몇번째 프래그먼트가 선택되었는지 인덱스 값을 받도록하고
    //만약 필요하면 추가적으로 bundle 안에 데이터를 넣어서 프래그먼트쪾으로 전달할 수 있도록함
    public  void onFragmentSelected(int position, Bundle bundle);
}
