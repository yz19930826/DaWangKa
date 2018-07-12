package club.hellojava.inter;

public interface OkHttpUtils{
    String get(String url) throws Exception;
    String post(String url,String json) throws Exception;
}
