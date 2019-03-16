package com.example.a82107.mymovieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a82107.mymovieapi.data.MovieInfo;
import com.example.a82107.mymovieapi.data.MovieList;
import com.example.a82107.mymovieapi.data.ResponseInfo;
import com.google.gson.Gson;

/*
{
"message": "movie readMovieList 성공",
"code": 200,
"resultType": "list",
"result": [
{
"id": 1,
"title": "꾼",
"title_eng": "The Swindlers",
"date": "2017-11-22",
"user_rating": 4.1,
"audience_rating": 8.36,
"reviewer_rating": 4.33,
"reservation_rate": 61.69,
"reservation_grade": 1,
"grade": 15,
"thumb": "http://movie2.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg?type=m99_141_2",
"image": "http://movie.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg"
},
{
"id": 2,
"title": "저스티스 리그",
"title_eng": "Justice League",
"date": "2017-11-15",
"user_rating": 4,
"audience_rating": 7.99,
"reviewer_rating": 5.83,
"reservation_rate": 12.63,
"reservation_grade": 2,
"grade": 12,
"thumb": "http://movie2.phinf.naver.net/20170925_296/150631600340898aUX_JPEG/movie_image.jpg?type=m99_141_2",
"image": "http://movie.phinf.naver.net/20170925_296/150631600340898aUX_JPEG/movie_image.jpg"
},
{
"id": 3,
"title": "토르:라그나로크",
"title_eng": "Thor: Ragnarok",
"date": "2017-10-25",
"user_rating": 3.7,
"audience_rating": 9.03,
"reviewer_rating": 6.13,
"reservation_rate": 6.73,
"reservation_grade": 3,
"grade": 12,
"thumb": "http://movie2.phinf.naver.net/20170928_85/1506564710105ua5fS_PNG/movie_image.jpg?type=m99_141_2",
"image": "http://movie.phinf.naver.net/20170928_85/1506564710105ua5fS_PNG/movie_image.jpg"
},
* */


//http://boostcourse-appapi.connect.or.kr:10000/movie/readMovieList?type=1
public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMovieList();
            }
        });

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void requestMovieList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1"; //파리미터도 추가해줌

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 받음 => " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 발생 => " + error.getMessage());
                    }
                }
        );
        //아래 add코드처럼 넣어줄때 Volley라고하는게 내부에서 캐싱을 해준다, 즉, 한번 보내고 받은 응답결과가 있으면
        //그 다음에 보냈을 떄 이전 게 있으면 그냥 이전거를 보여줄수도  있다.
        //따라서 이렇게 하지말고 매번 받은 결과를 그대로 보여주기 위해 다음과같이 setShouldCache를 false로한다.
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);//리퀘스트큐에 넣으면 리퀘스트큐가 알아서 스레드로 서버에 요청해주고 응답가져옴
        println("영화목록 요청 보냈음 : ");

    }

    public void processResponse(String response) {
        Gson gson = new Gson();

        //먼저 앞에 3개를 자바객체 값에 담게 변환시켜줌( result는 객체배열이므로 따로 또 클래스만들어줘야함, 따로 해주기위해 이거 한 후에 뒤에서 변환시킴)
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if (info.code == 200) { //코드가 200과 같다면 result라는거안에 데이터가 들어가있다는것을 확신할 수 있음
            MovieList movieList = gson.fromJson(response, MovieList.class);  //result부분 json속성값들 자바객체 값에 담게 변환시켜줌
            println("영화 갯수 : " + movieList.result.size());

            //영화 정보 출력
            // (영화정보에서 더 상세정보를 볼려면 id를 이용해서 volley요청을 또 보내면 될것이다.(id값으로 상세정보가 구분되니깐)
            //그리고 만약 그 상세한 정보를 MovieInfo로 그대로 받고 싶다면 MovieInfo의 속성을 더추가해서
            //그 영화상세 정보를 Gson으로 파싱하도록 만들어주면 된다.
            for (int i = 0; i < movieList.result.size(); i++) {
                MovieInfo movieInfo = movieList.result.get(i);
                println("영화 # " + i + " => " + movieInfo.id + ", " + movieInfo.title + ", " + movieInfo.grade);
            }

            //예를들어 한줄평리스트(commentList)라고 하는것을 정의한다면
            //이것을 리스트뷰에 보여줄 데이터 즉 어뎁터안에 ArrayList로
            //들어가는것과 포맷을 똑같이 해주면 (그니까 객체의 타입을 동일하게 해주면)
            //그걸 Gson으로 파싱한걸 그대로 어뎁터에 넣어주면 리스트뷰로 바로 보일거다.
            //그 후 notifyDataSetChanfed만 하면된다.(데이터 변경됬으니깐 업데이트하란말임)


        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
