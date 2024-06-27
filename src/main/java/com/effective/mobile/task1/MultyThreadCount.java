package com.effective.mobile.task1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultyThreadCount {
    private static int count = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (count < 10_000) {
            executorService.submit(() -> {
                try {
                    count();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).get();
        }
    }

    private static void count() throws InterruptedException {
        count++;
        System.out.println("Thread name " + Thread.currentThread().getName() + " Count is " + count);
    }
}
