package com.example.a82107.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

//내가 만든 인터페이스를 추가함(FragmentCallback)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentInfoCall, MovieInfoFunction {

    ViewPager pager;
    Fragment asura;
    Fragment gongzo;
    Fragment gundo;
    Fragment lucky;
    Fragment residenteEvil;
    Fragment theKing;
    //상세정보 프래그먼트용(MovieFragment에서 상세정보 버튼눌렀을 때 임시참조용
    Fragment movieInfo;
    //영화상세정보화면 기능 인터페이스
    MovieInfoFunction movieInfoFunction;
    //영화상세정보 프래그먼트
    Fragment gundoInfo;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //툴바가 xml 레이아웃으로 정의가 되면 그거를 이 화면에 설정할 때는 이런식으로 툴바를 다시 설정을 해줘야한다.
        //mainxml에서 AppBarLayout이라고 하는걸로 액션바 부분을 잡아주게되면 기본적으로 이코드를 넣어야된다고 생각하면됨
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //바로가기 메뉴에서 메뉴가 하나 선택되면 이 리스너가 호출만듬.
        navigationView.setNavigationItemSelectedListener(this);


        //프래그먼트(영화리스트)
        asura = new AsuraMovieFragment();
        gongzo = new GongzoMovieFragment();
        gundo = new GundoMovieFragment();
        lucky = new LuckyMovieFragment();
        residenteEvil = new ResidentevilMovieFragment();
        theKing = new ThekingMovieFragment();
        //프래그먼트(영화 상세정보)
        gundoInfo = new GundoInfoFragment();
        //뷰페이저
        pager = (ViewPager) findViewById(R.id.pager);
        // Disable clip to padding(뷰페이저에서 패래그먼트 옆화면 살짝보이게하기조절)
        pager.setClipToPadding(false);
        // set padding manually, the more you set the padding the more you see of prev & next page(뷰페이저에서 패래그먼트 옆화면 살짝보이게하기조절)
        pager.setPadding(100, 0, 100, 0);
        //캐싱을 해놓을 프래그먼트 개수
        pager.setOffscreenPageLimit(6);

        //getSupportFragmentManager로 프래그먼트 참조가능
        MoviePagerAdapter movieAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        movieAdapter.addItem(gundo);
        movieAdapter.addItem(gongzo);
        movieAdapter.addItem(theKing);
        movieAdapter.addItem(residenteEvil);
        movieAdapter.addItem(lucky);
        movieAdapter.addItem(asura);
        pager.setAdapter(movieAdapter);


        //getSupportFragmentManager().beginTransaction().add(R.id.container, gundo).commit();
    }

    @Override
    public void callMovieInfo(String movieTitle, String message) {
        if (movieTitle == "gundo") {
            //군도영화에 해당하는 프래그먼트 띄워줌
            movieInfo = gundoInfo;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, movieInfo ).commit();
        }
        /*Fragment curFragment = null;

        //해당 포지션에 맞게 툴바 타이틀 이름 바꿔줌
        if (position == 0) {
            curFragment = fragment1;
            toolbar.setTitle("첫번째 화면");
        } else if (position == 1) {
            curFragment = fragment2;
            toolbar.setTitle("두번째 화면");
        } else if (position == 2) {
            curFragment = fragment3;
            toolbar.setTitle("세번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.nav_0) {
            Toast.makeText(this, "군도 메뉴선택됨", Toast.LENGTH_LONG).show();
            //내가 만든 인터페이스 함수 호출
            onFragmentSelected(0, null);
        } else if (id == R.id.nav_1) {
            Toast.makeText(this, "두번째 메뉴선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if (id == R.id.nav_2) {
            Toast.makeText(this, "세번째 메뉴선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelected(2, null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    //MovieInfoFunction 인터페이스 구현 (상세정보 프래그먼트와 소통)
    public void showCommentWriteActivity(){
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivityForResult(intent, 101);
    }
    //MovieInfoFunction 인터페이스 구현 (상세정보 프래그먼트와 소통)
    public void showViewAllActivity(){
        Intent intent = new Intent(getApplicationContext(), ViewAllActivity.class);
        //getCount를 for문에 하면 계속 참조하므로 밖에 뺴준다.
        int count = GundoInfoFragment.adapter.getCount();
        ArrayList<CommentItem> commentItems = new ArrayList<CommentItem>(count);

        for(int i =0; i <count; i++){
            commentItems.add((CommentItem) GundoInfoFragment.adapter.getItem(i));
        }
        //prarcelable을 구현한 객체를 담은 ArrayList 데이터를 보냄
        //객체만 보낼떈 그냥 putExtra로함 =
        //ex) intent.puExtra("ex", new CommentItem(new CommentItem(R.drawable.user1, 5,"wow123", "너무 재밌어요");
        intent.putParcelableArrayListExtra("commentItems", commentItems);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 102);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //작성하기 결과
        if(requestCode == 101){
            if(intent != null){
                float ratingScore = intent.getFloatExtra("ratings", 5);
                String contents = intent.getStringExtra("contents");
                GundoInfoFragment.adapter.addItem(new CommentItem(R.drawable.user1, ratingScore,"JIN210", contents));
                //어댑터갱신
                GundoInfoFragment.adapter.notifyDataSetChanged();

            }
        }
        //모두보기 결과
        else if(requestCode == 102){
            //모두보기화면에서 작성한 데이터들을 모두 가져옴
            ArrayList<CommentItem> intentItems = intent.getParcelableArrayListExtra("intentItems");
            //리스트뷰에추가
            for(int i=0; i <intentItems.size();i++){
                GundoInfoFragment.adapter.addItem(intentItems.get(i));
            }
            GundoInfoFragment.adapter.notifyDataSetChanged();
        }
    }



    //영화리스트 뷰페이저 어댑터(어댑터 안에서 각각의 영화를 데이터로서 관리한다)
    class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> movies = new ArrayList<Fragment>();

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            movies.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return movies.get(position);
        }

        @Override
        public int getCount() {
            return movies.size();
        }
    }


}
