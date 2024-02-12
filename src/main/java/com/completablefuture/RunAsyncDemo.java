package com.completablefuture;

import com.completablefuture.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

public class RunAsyncDemo {

    public void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });

                    /**
                     * Ideally we should write logic to save list of employee to database like: repository.saveAll(employees);
                     * but since this is just learning demo for runAsync we are only printing employees list size here.
                     * Notice: Here we are using runAsync (new Runnable()) method, and it gets common thread from ForkJoinPool.
                     */
                    System.out.println("Thread name : " + Thread.currentThread().getName());
                    System.out.println(employees.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * runAsyncFuture.toCompletableFuture(); - Lets say your thread is blocked for some reason, and runAsyncFuture.get() is waiting
         * for thread to complete its execution then in such cases, you can use toCompletableFuture() to complete the execution forcefully.
         * This feature was not available in future class. So this is one advantage of using CompletableFuture over Future
         */

        runAsyncFuture.get();
    }

    public void saveEmployeesWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() ->  {
                try {
                    List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });

                    /**
                     * Here we are using runAsync (new Runnable(), executor) method and it gets thread from custom executor
                     * which we created using the Executor. If you provide your own executor then it will get the thread from
                     * there. However, if you provide runAsync (new Runnable()) then it gets thread from ForkJoinPool
                     */
                    System.out.println("Thread name : " + Thread.currentThread().getName());
                    System.out.println(employees.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }, executor);
        runAsyncFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        runAsyncDemo.saveEmployees(new File("/Users/abgwal/Projects/java8/src/main/java/com/resources/data/employees.json"));
        runAsyncDemo.saveEmployeesWithCustomExecutor(new File("/Users/abgwal/Projects/java8/src/main/java/com/resources/data/employees.json"));

    }
}
