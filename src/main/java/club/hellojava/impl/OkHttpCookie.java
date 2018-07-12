package club.hellojava.impl;

import club.hellojava.inter.OkHttpUtils;
import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OkHttpCookie implements OkHttpUtils{



    public static final MediaType JSON = MediaType.parse(
            "application/json;charset=utf-8"
    );


    //接收cookie和发送cookie
    private static CookieJar cookieJar = new CookieJar() {
        private final Map<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookieList) {
            cookieStore.put(httpUrl.host(), cookieList);
            List<Cookie> cookies = cookieStore.get(httpUrl.host());
        }

        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            List<Cookie> cookies = cookieStore.get(httpUrl.host());
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };


    private OkHttpClient httpClient;

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    {
        builder.cookieJar(cookieJar);
        httpClient = builder.build();
    }

    public String get(String url) throws Exception {


        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();

    }


    /**
     * 自动管理Cookie的Post，并返回response返回体内容
     * @param url url地址
     * @param json json数据
     * @return
     * @throws Exception
     */
    public String post(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }


    public static void main(String[] args) {

    }
}
