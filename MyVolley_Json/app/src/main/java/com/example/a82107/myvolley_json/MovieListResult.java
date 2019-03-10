package com.example.a82107.myvolley_json;

import java.util.ArrayList;

public class MovieListResult {//2빠따

    //각 속성의 이름과 값의 타입에 맞게 코딩해줌
    String boxofficeType;
    String showRange;
    //배열은 List형태로 받아주면 된다.
    //여기서는 배열안에 또 객체가 들어가있으므로 <객체>를 해준다.
    ArrayList<Movie> dailyBoxOfficeList = new ArrayList<Movie>();


}
