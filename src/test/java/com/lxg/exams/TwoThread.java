package com.lxg.exams;

public class TwoThread {
    static final Object lock = new Object();
    static boolean t2Run = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                while(!t2Run){ //此处不可为if(!t2Run),否则会出现假唤醒的情况，即唤醒后会在执行完lock.wait()停止了，不会执行下面的代码
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+":a");
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+":b");
                t2Run = true;
                lock.notify();
            }
        },"t2");
        t2.start();
        t1.start();

    }
}