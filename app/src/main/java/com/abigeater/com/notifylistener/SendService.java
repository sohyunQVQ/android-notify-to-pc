package com.abigeater.com.notifylistener;


import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendService {

    static String PcId;

    public static String getIp(){
        return PcId;
    }

    public static void setIp(String strIP){
        PcId = "http://"+strIP+":5000";
    }

    public static boolean send(final String sender,final String content){
        Log.i("post", "1");
        new Thread(new Runnable() {
            @Override
            public void run() {

                String receIp = getIp();
                if(receIp == null) {
                    return ;
                }
                Log.i("POST", receIp);

                if(!receIp.equals("")){
                    OkHttpClient client = new OkHttpClient();
                    MediaType JSON = MediaType.get("application/json; charset=utf-8");
                    String json = "{\"sender\": \""+sender+"\", \"content\": \""+content+"\"}";
                    RequestBody body = RequestBody.create(json, JSON);
                    Request request = new Request.Builder()
                            .url(receIp)
                            .post(body)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();
                        Log.i("POST", response.body().string());

                    } catch (IOException e){
                        Log.i("POST ERROR", e.getMessage());
                    }
                }
            }
        }).start();

        return true;
    }
}
