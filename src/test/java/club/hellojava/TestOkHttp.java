package club.hellojava;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOkHttp {
    CookieJar cookieJar = new CookieJar() {
        //Cookie缓存区
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

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    @Test
    public void testCookie() throws IOException {
        OkHttpClient.Builder builder = this.builder.cookieJar(cookieJar);
        OkHttpClient okHttpClient = builder.build();

        //构造Request对象
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        for (int i = 0; i < 2; i++) {
            Response response = okHttpClient.newCall(request).execute();

            System.out.println(response.body().string());
        }

    }
}
