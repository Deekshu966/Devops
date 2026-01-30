package com.wip;

public class MainClass {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {

        MainClass mc = new MainClass();
        System.out.println(mc.add(10, 20));
        System.out.println("Java For Docker!!!! This code is pulled from WIP GitHub Repository");
        System.out.println("This is the updated code after adding Jenkinsfile to the repository");

     
        Thread keepAliveThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Application is running...");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted, shutting down...");
            }
        });

        keepAliveThread.setDaemon(false); 
        keepAliveThread.start();
    }
}
