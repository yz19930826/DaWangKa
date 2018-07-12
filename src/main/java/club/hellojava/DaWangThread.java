package club.hellojava;

import club.hellojava.impl.OkHttpSimple;

public class DaWangThread implements Runnable {
    private OkHttpSimple simple = new OkHttpSimple();

    public void run() {
        for (int i = 0; i < 100; i++) {

            simple.daWang();
        }
    }
}
