package main.java.com.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.get();
        /**
         * Unlike future, in completableFuture if thread is taking long time to complete its task then you can
         * forcefully complete it using complete() method
         */
        completableFuture.complete("complete() here forcefully completes the operation, instead " +
                "of waiting for thread to complete. Good idea is to return some cached value or template " +
                "to show to the user");
    }

}
