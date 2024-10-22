package com.example.mysummary6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AppHelper {
    private static final String TAG = "AppHelper";

    private static SQLiteDatabase database;

    private static String createTableOutlineSql = "create table if not exists outline" +
            "(" +
            "    _id integer PRIMARY KEY autoincrement, " +  //_id는 SQLite에서 내부적으로 관리되는 내부 id이다.
            "   id  integer, " +
            "   title text, " +
            "   title_eng text, " +
            "   dataValue text, " +  //date는 예약어라 dataValue라함
            "   user_rating float, " +
            "   audience_rating float, " +
            "   reviewer_rating float, " +
            "   reservation_rate float, " +
            "   reservation_grade integer, " +
            "   grade integer, " +
            "   thumb text, " +
            "   image text" +
            ")";

    // 1단계: 데이터베이스(저장소)를 만들거나 오픈하는 단계
    public static void openDatabase(Context context, String databaseName) {
        println("openDatabase 호출됨.");
        try {
            ///SQLiteDatabase객체가 리턴됨
            database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
            if (database != null) {
                println("데이터베이스 " + databaseName + " 오픈됨");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2단계 테이블 생성
    public static void createTable(String tableName) {
        println("createTable 호출됨 : " + tableName);

        if (database != null) {
            if (tableName.equals("outline")) {
                database.execSQL(createTableOutlineSql);
                println("outline 테이블 생성 요청됨.");
            }

        } else {
            println("데이터베이스를 먼저 오픈 해야합니다.");
        }
    }

    public static void println(String data) {
        Log.d(TAG, data);
    }

}
