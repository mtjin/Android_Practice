package com.example.a82107.myvolley_image;

// 이건 예시고 실제 응답받는 json은 더길다. 일부만 여기다 주석으로 갖다논거이다. 실제론 dailyBoxOfficeList 뒤에 다른 영화들 더 있음

/*
{
  "boxOfficeResult": {
    "boxofficeType": "일별 박스오피스",
    "showRange": "20120101~20120101",
    "dailyBoxOfficeList": [
      {
        "rnum": "1",
        "rank": "1",
        "rankInten": "0",
        "rankOldAndNew": "OLD",
        "movieCd": "20112207",
        "movieNm": "미션임파서블:고스트프로토콜",
        "openDt": "2011-12-15",
        "salesAmt": "2776060500",
        "salesShare": "36.3",
        "salesInten": "-415699000",
        "salesChange": "-13",
        "salesAcc": "40541108500",
        "audiCnt": "353274",
        "audiInten": "-60106",
        "audiChange": "-14.5",
        "audiAcc": "5328435",
        "scrnCnt": "697",
        "showCnt": "3223"
      },
*/
public class MovieList { //1빠따

    //일단 JSON이라고 하는 응답을 받아서, 문자열을 받아 자바 객체로 만들어야 되니까
    //그렇다면 자바 객체는 클래스가 있어야 자바 객체로 만드므로 이 클래스를 만들어줌

    //먼저 가장 바깥쪽에 있는 boxOfficeResult 가 객체의 이름이다. 이것의 타입은 객체이므로 또 객체를 하나 더 만들어야한다.=> MovieListResult
    MovieListResult boxOfficeResult;


}
