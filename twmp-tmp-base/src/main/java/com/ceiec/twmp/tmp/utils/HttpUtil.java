package com.ceiec.twmp.tmp.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @title: HttpUtil </br>
 * @createDate: 2019/4/19 10:43 </br>
 * @author: shihsh  </br>
 * @description: java模拟HTTP请求 </br>
 * @version: V1.0
 **/


public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static int  CONNECT_TIME_OUT = 10;

    private static int READ_TIME_OUT = 20;

    private static int WRITE_TIME_OUT = 30;

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");

    public static String sendPost(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
