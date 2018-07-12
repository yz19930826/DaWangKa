package club.hellojava;

import club.hellojava.impl.OkHttpSimple;

import java.io.IOException;

/**
 * 线程类
 * 根据指定的url执行get或post操作
 */
public class RefreshThread implements Runnable {
    private OkHttpSimple utils = new OkHttpSimple();
    private String url;
    private String name;
    private int numCount;
    private int num;


    public RefreshThread(String name, String url, int num) {
        this.name = name;
        this.url = url;
        this.num = num;
    }

    public void run() {
        System.out.println(name+",start！");
        while ((num--) != 0) {
            try {
                System.out.println("线程名" + name);
                System.out.println("共执行次数:" + (++numCount));
                utils.get(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
