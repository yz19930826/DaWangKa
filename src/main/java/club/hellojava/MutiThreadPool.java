package club.hellojava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutiThreadPool {
    private static ExecutorService es;
    public  MutiThreadPool(){

    }
    public MutiThreadPool(Integer threadNum){
        es = Executors.newFixedThreadPool(threadNum);
    }
    public ExecutorService getpool(){
        return es;
    }
}
