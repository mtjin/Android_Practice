package com.mtjinse.fortunecookieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    int imgInedex = 1;
    ImageView cookieImage;
    TextView fortuneText;

    final String FORTUNE_BASE_URL = "http://memolease.ipdisk.co.kr:1337";
    Retrofit retrofit;
    FortuneService fortuneService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookieImage = findViewById(R.id.cookieImg);
        fortuneText = findViewById(R.id.fortuneText);

        //Retrofit 객체생성
        retrofit = new Retrofit.Builder()
                .baseUrl(FORTUNE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    /*addConverterFactory(GsonConverterFactory.create())은
    Json을 우리가 원하는 형태로 만들어주는 Gson라이브러리와 Retrofit2에 연결하는 코드입니다 */
        //FORTUNE_BASE_URL은 변경되지않는 url이다.

        fortuneService = retrofit.create(FortuneService.class); //url을 뒷부분 정확히 해주고(완벽한주소) 클래스로 연결

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (imgInedex) {
                    case 1:
                        cookieImage.setImageResource(R.drawable.cookie02);
                        imgInedex = 2;
                        break;
                    case 2:
                        cookieImage.setImageResource(R.drawable.cookie03);
                        imgInedex = 3;
                        break;
                    case 3:
                        cookieImage.setImageResource(R.drawable.cookie04);
                        imgInedex = 4;
                        break;
                    case 4:
                        cookieImage.setImageResource(R.drawable.cookie05);
                        imgInedex = 5;

                        //비동기적으로 서버에 요청
                        fortuneService.getFortune().enqueue(new Callback<List<Fortune>>() {
                            @Override
                            public void onResponse(Call<List<Fortune>> call, Response<List<Fortune>> response) { //서버로부터 응답을 받았을때
                                if(response.isSuccessful()){ //서버와 교신은 성공했고 데이터까지 잘 받았는지 검사
                                    List<Fortune> fortuneList = response.body(); //서버에서받아온갑값을 받아줌  //그냥 response로 받으면 꺠져서온다.
                                    Random random = new Random();
                                    int n = random.nextInt(fortuneList.size());

                                    Fortune fortune = fortuneList.get(n);
                                    fortuneText.setText(fortune.getContent());


                                }else{
                                    Toast.makeText(MainActivity.this, "서버에서 값을 받아오지 못했습니다", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Fortune>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "서버와 통신을 실패했습니다.",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 5:
                        cookieImage.setImageResource(R.drawable.cookie01);
                        imgInedex = 1;
                        break;

                }
            }
        });
    }
}
