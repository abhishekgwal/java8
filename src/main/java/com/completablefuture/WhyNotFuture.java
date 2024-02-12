package main.java.com.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<List<Integer>> future = executorService.submit(() -> {
            System.out.println("Thread : "+Thread.currentThread().getName());
        /**
         * Let's imagine that delay is making an API call here, and it takes around one minute for delay API to get the response.
         * So below thread is blocked for 1 minute. Now even if you try to manually complete the future execution forcefully
         * you cannot complete it. For example: if you type future.something() you won't get any method which lets you
         * finish the execution forcefully. So that's a drawback of using Future.
         * Another drawback of future is that multiple futures cannot be combined or chained together.
         */

            delay(1);
            return Arrays.asList(1, 2, 3, 4, 5);
        });

        List<Integer> integerList = future.get();
        System.out.println(integerList);

    }

    private static void delay(int min) {
        try {
            TimeUnit.MINUTES.sleep(min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
