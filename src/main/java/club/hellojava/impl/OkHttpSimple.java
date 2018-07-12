package club.hellojava.impl;

import club.hellojava.DaWangThread;
import club.hellojava.inter.OkHttpUtils;
import net.sf.json.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 简单的get和post
 * 不处理cookie
 */
public class OkHttpSimple implements OkHttpUtils {
    OkHttpClient client = new OkHttpClient();


    public static final MediaType JSON = MediaType.parse(
            "application/json;charset=utf-8"
    );

    /**
     * 普通Get请求
     *
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 带JSON数据的POST请求
     *
     * @param url  网址
     * @param json json数据
     * @return
     * @throws IOException
     */
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 筛选大王卡号
     */
    public void daWang(){
        try {
            String s = get("https://m.10010.com/NumApp/NumberCenter/qryNum?callback=jsonp_queryMoreNums&provinceCode=11&cityCode=110&monthFeeLimit=0&groupKey=85236889&searchCategory=3&net=01&amounts=200&codeTypeCode=&searchValue=&qryType=02&goodsNet=4&_=1517462421655");
//        System.out.println(s);
            if (s != null) {
                int start = s.indexOf("{");
                int end = s.lastIndexOf("}");

                String substring = s.substring(start, end + 1);

                //转json
                JSONObject jsonObject = JSONObject.fromObject(substring);
                Object numArray = jsonObject.get("numArray");
                String tempString = numArray.toString();
                int length = tempString.length();

                String result = null;


                result = tempString.substring(1, length - 1);


//            System.out.println(result);

                //将号码截取出来 0 12 24
                String[] resultArray = result.split(",");
                for (int i = 0; i < resultArray.length; i++) {
                    if (i % 12 == 0) {
//                    System.out.println(resultArray[i]);

                        if (!resultArray[i].startsWith("176")) {
                            System.out.println(resultArray[i]);
                        }
                        if (resultArray[i].startsWith("188")) {
                            System.out.println("超级关注：" + resultArray[i]);
                        }
                        if (resultArray[i].startsWith("186")) {
                            System.out.println("重点关注对象：" + resultArray[i]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {


        new Thread(new DaWangThread()).start();
        new Thread(new DaWangThread()).start();
        new Thread(new DaWangThread()).start();

    }
}
