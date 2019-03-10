package com.example.a82107.myvolley_json;

import com.android.volley.RequestQueue;

//pplication 클래스를 정의하고 앱에 등록하여 사용하는 경우에는 이 Application 클래스 안에 넣어둘 수도 있고
// AppHelper와 같은 별도의 클래스를 만들어 그 안에 넣어둘 수도 있습니다. 여기서는 후자방법을 사용했습니다.
public class AppHelper {
    //static으로 했으므로 어디서나 참조가능하게해놨음
    public static RequestQueue requestQueue;

    //먼저 요청(Request) 객체를 만들고 이 요청 객체를 요청 큐(RequestQueue)라는 곳에 넣어주기만 하면 됩니다.
    //그러면 요청 큐가 알아서 웹서버에 요청하고 응답까지 받아 사용자가 사용할 수 있도록 지정된 메소드를 호출해줍니다.
}
