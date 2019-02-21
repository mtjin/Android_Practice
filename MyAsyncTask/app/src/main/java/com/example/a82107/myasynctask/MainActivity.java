package com.example.a82107.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progress;
    //백그라운드Task 정의
    BackgroundTask task;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        progress = (ProgressBar) findViewById(R.id.progress);

        // 실행 버튼 이벤트 처리
        Button executeButton = (Button) findViewById(R.id.executeButton);
        executeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 새로운 Task 객체를 만든다
                task = new BackgroundTask();
                //excute를 통해 실행시킨다
                //여기선 100을 매개변수로 보내는데 여기 예제에서는 이 매개변수를 doInBackGround에서 사용을 안했다.
                task.execute(100);
            }
        });

        // 취소 버튼 이벤트 처리
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //task종료함 onCancled호출된다.
                //이미 스레드가 종료된 뒤에 cancel하면 아무효과없음
                task.cancel(true);
            }
        });
    }

    //새로운 TASK정의 (AsyncTask)
    // < >안에 들은 자료형은 순서대로 doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형을 뜻한다.(내가 사용할 매개변수타입을 설정하면된다)
    class BackgroundTask extends AsyncTask<Integer , Integer , Integer> {
        //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
        protected void onPreExecute() {
            value = 0;
            progress.setProgress(value);
        }

        //스레드의 주작업 구현
        //여기서 매개변수 Intger ... values란 values란 이름의 Integer배열이라 생각하면된다.
        //배열이라 여러개를 받을 수 도 있다. ex) excute(100, 10, 20, 30); 이런식으로 전달 받으면 된다.
        protected Integer doInBackground(Integer ... values) {
            //isCancelled()=> Task가 취소되었을때 즉 cancel당할때까지 반복
            while (isCancelled() == false) {
                value++;
                //excute(100)의 100을 사용할려면 이런식으로 해줘도 같은 결과가 나온다.
                //if (value >= values[0].intValue())
                if (value >= 100) {
                    break;
                } else {
                    //publishProgress()는 onProgressUpdate()를 호출하는 메소드(그래서 onProgressUpdate의 매개변수인 int즉 Integer값을 보냄)
                    //즉, 스레드작업을 실행하면서 중간중간 진행상태같은것을 UI에 업데이트하도록하기위해 사용함
                    publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }

            return value;
        }

        //중간중간 진행사항을 UI에 업데이트
        //publishProgress(value)의 value를 값으로 받는다.values는 배열이라 여러개 받기가능
        protected void onProgressUpdate(Integer ... values) {
            progress.setProgress(values[0].intValue());
            textView.setText("현재 진행 값 : " + values[0].toString());
        }


        //이 Task에서(즉 이 스레드에서) 수행되던 작업이 종료되었을 때 호출됨
        protected void onPostExecute(Integer result) {
            progress.setProgress(0);
            textView.setText("완료되었습니다");
        }

        //Task가 취소되었을때 호출
        protected void onCancelled() {
            progress.setProgress(0);
            textView.setText("취소되었습니다");
        }
    }
}
