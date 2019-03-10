package com.example.a82107.myvolley_json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/*웹서버에 요청하고 응답을 받을 때는 HttpURLConnection을 사용할 수 있습니다.

하지만 요청과 응답을 위한 코드의 양이 많은 데다가 스레드를 사용하면서 더 많은 코드를 넣어주게 됩니다.

그렇다면 코드의 양을 좀 더 적게 할 수 있는 방법은 없을까요?

안드로이드에서 제공하는 HTTP 라이브러리를 사용하면 쉽고 빠르게 네트워크 통신을 할 수 있습니다.

여러 라이브러리 중에 많이 사용되는 대표적인 라이브러리인 Volley 라이브러리를 이용해 HTTP로 요청 하고 응답을 받는 방법에 대해 알아봅시다.
* */
//즉 위내용을 요약하면 requestQueue라고 하는거에서(Volley라이브러리) 스레드를 알아서 처리해주기 때문에
// 사용자가 스레드나 핸들러를 직접적으로 처리할 필요가 없어 코드양이 줄어들고 편리하다는 장점이있다.
//gradle에 Volley라이브러리 추가시켜야함
//manifest에서는 인터넷권한 추가
//-------------------------------------------------------------------------------------------------------------------------------------
//gradle에 gson라이브러리도 추가한다.
//Gson이 Json을 알아서 파싱해주니까 편하다는 장점이있음
//Json에 알맞게 객체와 객체안에 타입을 지정해주면됨
//정리하자면 gson을 사용할려면 일단 객체로 받아야하므로 객체를 하나 만들어야하고 그 객체(클래스)에서는 JSON 포맷에 맞게 JSON 속성에 맞게,
// 속성이름을 변수명으로 바꿔주고 이 속성이름이 가진 값의 타입에 맞게 변수명의 타입을 앞에 붙여준다.  객체가 나오는 경우는 객체를 추가하면된다.
//json 받아오는 주소 : http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101
//보기좋게 변환해주는 사이트 : http://jsonviewer.stack.hu/#http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101
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
                sendRequest();
            }
        });

        if(AppHelper.requestQueue == null){
            //리퀘스트큐 생성 (MainActivit가 메모리에서 만들어질 때 같이 생성이 될것이다.
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest(){
        //String url = "https://www.google.co.kr";
        String url = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";


        //StringRequest를 만듬 (파라미터구분을 쉽게하기위해 엔터를 쳐서 구분하면 좋다)
        //StringRequest는 요청객체중 하나이며 가장 많이 쓰인다고한다.
        //요청객체는 다음고 같이 보내는방식(GET,POST), URL, 응답성공리스너, 응답실패리스너 이렇게 4개의 파라미터를 전달할 수 있다.(리퀘스트큐에 ㅇㅇ)
        //화면에 결과를 표시할때 핸들러를 사용하지 않아도되는 장점이있다.
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {  //응답을 문자열로 받아서 여기다 넣어달란말임(응답을 성공적으로 받았을 떄 이메소드가 자동으로 호출됨
                    @Override
                    public void onResponse(String response) {
                        println("응답 => " + response);

                        processResponse(response);// 응답을 처리하는 메소드를 하나구현한다.
                    }
                },
                new Response.ErrorListener(){ //에러발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 => "+ error.getMessage());
                    }
                }
        ){
            //만약 POST 방식에서 전달할 요청 파라미터가 있다면 getParams 메소드에서 반환하는 HashMap 객체에 넣어줍니다.
            //이렇게 만든 요청 객체는 요청 큐에 넣어주는 것만 해주면 됩니다.
            //POST방식으로 안할거면 없어도 되는거같다.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        //아래 add코드처럼 넣어줄때 Volley라고하는게 내부에서 캐싱을 해준다, 즉, 한번 보내고 받은 응답결과가 있으면
        //그 다음에 보냈을 떄 이전 게 있으면 그냥 이전거를 보여줄수도  있다.
        //따라서 이렇게 하지말고 매번 받은 결과를 그대로 보여주기 위해 다음과같이 setShouldCache를 false로한다.
        //결과적으로 이전 결과가 있어도 새로 요청한 응답을 보여줌
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("요청 보냄!!");
    }

    public void processResponse(String response){
        Gson gson = new Gson();
        //응답으로 받은 문자열과 어떤자바객체로 바꿔줄지 정한걸 매개변수로함
        //MovieList객체로 바꿔달라했으니깐 리턴 타입은 MovieList가 된다.
        //첫번째매개변수에는 응답으로받은 문자열을 넣어준다.(JSON문자열일거다)
        MovieList movieList =  gson.fromJson(response, MovieList.class);

        if(movieList != null){ //잘 받았는지 체크해봄
            int countMovie = movieList.boxOfficeResult.dailyBoxOfficeList.size(); //dailyBoxOfficeList
            println("박스오피스 타입 : " + movieList.boxOfficeResult.boxofficeType);
            println("응답받은 영화 개수 : " + countMovie);
        }
    }


    public void println(String data){
        textView.append(data + "\n");
    }
}
