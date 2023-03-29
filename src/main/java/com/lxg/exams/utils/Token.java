package com.lxg.exams.utils;


import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;


/**
 * 获取token
 */
public class Token {

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static String getToken(String cid,String csid) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
            .url("https://aip.baidubce.com/oauth/2.0/token?client_id="+cid+"&client_secret="+csid+"&grant_type=client_credentials")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build();
        Response response = HTTP_CLIENT.newCall(request).execute();


        JSONObject jsonObject = new JSONObject(response.body().string());
        String access_token = jsonObject.getString("access_token");
        return access_token;


    }
}