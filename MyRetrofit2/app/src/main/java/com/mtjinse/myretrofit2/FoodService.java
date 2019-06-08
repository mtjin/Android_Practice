package com.mtjinse.myretrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {
    /**
     * GET 방식, URL/fortunes 호출.
     * Json Type의 Data를 통신을 통해 받음.
     * "http://memolease.ipdisk.co.kr:1337/foods" 이 최종 호출 주소.
     * @return JSON 데이터를 원하는 형태의 Data모습으로 변환하여 리스트 형태로 반환.
     */
    @GET("/foods")
    Call<List<Food>> getFood();
}
