package com.example.a82107.mymovieapi.data;
/*
"message": "movie readMovieList 성공",
"code": 200,
"resultType": "list",
"result":
* */
public class ResponseInfo {
    public String message; //다른데서도 사용할 수 있게 public을 붙여줌
    public int code;
    public String resultType;
    //public String result;  //result가 String인줄 알고 변환해줄려했는데 resultType이 배열이여서 주석안하면 에러남
}
