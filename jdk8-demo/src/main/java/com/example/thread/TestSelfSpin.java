package com.example.thread;

/**
 * @author Javen
 * @date 2022/3/17
 */
public class TestSelfSpin {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.activeCount());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end...");
            }
        }).start();

        // 这里为自旋，引起CPU空转造成性能浪费
        while (true) {
            if (Thread.activeCount() == 2) break;
        }

        System.out.println(Thread.currentThread().getName() + " end...");
    }
}
