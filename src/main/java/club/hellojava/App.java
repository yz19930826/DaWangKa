package club.hellojava;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Hello world!
 *
 */
public class App 
{
//    private static String url = "http://blog.csdn.net/yz19930826/article/details/79041310";
    private static String url = "https://www.baidu.com/link?url=G9vEJnEvnFw_hsl6esTZ-GTzg0JNBmmzqVuhFwfMrSAR2ZX39LLwkzjpuOXUp8bpM1EbHpk9lwS118yvJHDp0nN_eCd7IrGbKnOu9K2d8YG&wd=&eqid=d7549e3500027ae4000000025a5bf2c1";
//    private static String url = "https://www.baidu.com/link?url=K_MqdamTvWT2tzFHuhYyk3J9H1Ba5tuWpX2kTbxF9kaikAqvbRTGhtc_y_QRXXdxaTPgbj2tlfJFmZQnwBmWCmw7lg26ClN4TNlTIMjU5dC&wd=&eqid=f17b473b00026615000000025a5bf045";

    public static void main( String[] args ) throws IOException {
        MutiThreadPool pool = new MutiThreadPool(5);
        ExecutorService es = pool.getpool();

        es.submit(new RefreshThread("thread1",url,5000));
        es.submit(new RefreshThread("thread2",url,5000));
        es.submit(new RefreshThread("thread3",url,5000));
        es.submit(new RefreshThread("thread4",url,5000));
        es.submit(new RefreshThread("thread5",url,5000));

//        System.out.println("end");

//        new Thread(new RefreshThread("thread1",url,100)).start();

        System.in.read();
    }
}
