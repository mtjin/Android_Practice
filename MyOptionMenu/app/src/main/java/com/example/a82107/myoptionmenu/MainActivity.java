package com.example.a82107.myoptionmenu;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //만약 res/values/styles에서 style태그를
    //parent="Theme.AppCompat.Light.NoActionBar"로 바꾸면 최상단의 액션바가 사라질 것이다.
    //아니면 밑에처럼 hide매소드를 사용해서 액션바를 안보이게 할 수도 있다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
    }

    //optionsMenu =>안드로이드에서의 기본메뉴
    //이렇게하면 메뉴가 화면에 보이게된다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //메뉴 아이템이 클릭됐을때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int currentId = item.getItemId();
        switch (currentId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴 클릭됨.", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴 클릭됨", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴 클릭됨", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
