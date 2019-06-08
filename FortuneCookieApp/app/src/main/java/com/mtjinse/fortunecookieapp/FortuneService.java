package com.mtjinse.fortunecookieapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/*  @GET
    @POST
    @DELETE
    @PUT*/
public interface FortuneService {
    /**
     * GET 방식, URL/fortunes 호출.
     * Json Type의 Data를 통신을 통해 받음.
     * "http://memolease.ipdisk.co.kr:1337/fortunes" 이 최종 호출 주소.
     * @return JSON 데이터를 원하는 형태의 Data모습으로 변환하여 리스트 형태로 반환.
     */
    @GET("/fortunes")
    Call<List<Fortune>> getFortune();  //<Fortune> 이라고하면 하나만받아온다. <>사이에는 어떤형태로 달라고 정할 수 있는 명령어이다.
    
}
