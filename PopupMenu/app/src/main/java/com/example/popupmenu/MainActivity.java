package com.example.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 뷰의 주소 값을 받는다.
        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view){
        // PopupMenu 객체를 생성한다.(이 버튼메소드와 text1을 연관시키는듯)
        PopupMenu pop = new PopupMenu(this, text1);

        // PopupMenu 의 메뉴를 관리하는 객체를 추출한다.
        Menu menu = pop.getMenu();

        // 메뉴를 구성한다.(위에서 추출한 메뉴를 관리하는 객체와 popup_menu를 매칭해줌)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);

        // 팝업 메뉴의 리스너를 설정한다.
        PopupListener listener = new PopupListener();
        pop.setOnMenuItemClickListener(listener);

        // 메뉴를 나타나게 한다.
        pop.show();
    }

    class PopupListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            // 사용자가 선택한 메뉴의 id 값을 추출한다.
            int id = item.getItemId();
            // 분기한다.
            switch (id){
                case R.id.item1 :
                    text1.setText("메뉴 1을 선택하였습니다");
                    break;
                case R.id.item2 :
                    text1.setText("메뉴 2를 선택하였습니다");
                    break;
                case R.id.item3 :
                    text1.setText("메뉴 3을 선택하였습니다");
                    break;
            }
            return false;
        }
    }
}







