package com.wip;

public class MainClass {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {

        System.out.println(new MainClass().add(10, 20));
        System.out.println("Java For Docker!!!! This code is pulled from WIP GitHub Repository");
        System.out.println("This is the updated code after adding Jenkinsfile to the repository");

        try {
            while (true) {
                System.out.println("Application is running...");
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            System.out.println("Application stopped");
        }
    }
}
