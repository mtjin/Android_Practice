package com.mtjinse.myretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    Retrofit retrofit;
    final String FORTUNE_BASE_URL = "http://memolease.ipdisk.co.kr:1337";
    FoodService foodService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView= findViewById(R.id.textView);

        //Retrofit 객체생성
        retrofit = new Retrofit.Builder()
                .baseUrl(FORTUNE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    /*addConverterFactory(GsonConverterFactory.create())은
    Json을 우리가 원하는 형태로 만들어주는 Gson라이브러리와 Retrofit2에 연결하는 코드입니다 */


        foodService = retrofit.create(FoodService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //API Service파일에서 만들어 두었던 getFortune() 메서드를 통해서 서버와 통신하는 코드
                foodService.getFood().enqueue(new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                        //서버와 통신이 성공하였을 때 실행되는 로직
                        //서버에서 받아온 Data를 리스트 변수에 담는 코드
                        List<Food> foodList = response.body();
                        Random random = new Random();
                        int n = random.nextInt(foodList.size());

                        Food food = foodList.get(n);
                        textView.setText(food.getContent().toString());
                    }


                    @Override
                    public void onFailure(Call<List<Food>> call, Throwable t) {
                        //서버와 통신이 실패하였을 때 실행되는 로직
                        Log.d("error", "서버와 통신에 실패했습니다.");
                    }
                });
            }
        });
    }
}
