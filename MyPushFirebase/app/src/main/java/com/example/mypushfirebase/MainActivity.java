package com.example.mypushfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView logOutput;

    EditText dataInput;
    TextView dataOutput;

    String regId;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOutput = findViewById(R.id.logOutput);
        dataInput = findViewById(R.id.dataInput);
        dataOutput = findViewById(R.id.dataOutput);

        getRegistrationId();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data  = dataInput.getText().toString().trim();
                send(data);
            }
        });

        queue = Volley.newRequestQueue(getApplicationContext());

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    //이미화면이 만들어져있을경우 인텐트만 처리가능하게함
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    public void processIntent(Intent intent){
        if(intent != null){
            String from = intent.getStringExtra("from");
            String contents = intent.getStringExtra("contents");

            println("DATA : " + from + ", " + contents);
            dataOutput.setText("DATA : " + contents);

        }
    }

    public void  getRegistrationId(){
        regId = FirebaseInstanceId.getInstance().getToken();
        println("등록 ID => " + regId);
    }

    public void  send(String input){
        JSONObject requsetData = new JSONObject();
        try {
            requsetData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requsetData.put("data", dataObj);

            JSONArray idArray =new JSONArray();
            idArray.put(0,regId); //수신자의 등록아이디
            requsetData.put("registration_ids", idArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendData(requsetData, new SendResponseListener() {
            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨");
            }

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨");
            }

            @Override
            public void onRequestError(VolleyError error) {
                println("onRequestError() 호출됨");
            }
        });
    }

    public interface SendResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener ){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestError(error);
                    }
                }
        ){
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization", "key=AIzaSyBtFkGMA0FwubWhrlZSmNpNkXRifPuXBJg");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        queue.add(request);
    }

    public void println(String data){
        logOutput.append(data + "\n");
    }
}
