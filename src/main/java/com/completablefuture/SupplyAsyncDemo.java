package com.completablefuture;

import com.completablefuture.database.EmployeeDatabase;
import com.completablefuture.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * supplyAsync comes with two overloaded methods - supplyAsync(Supplier<T>) and supplyAsync(Supplier<T>, Executor)
 */

public class SupplyAsyncDemo {

    /**
     * Here we are using supplyAsync(Supplier<T>) method, and it gets common thread from ForkJoinPool.
     */
    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> listCompletableFuture =
                CompletableFuture
                        .supplyAsync(() -> {
                            System.out.println("Executed By Thread: "+Thread.currentThread().getName());
                        return EmployeeDatabase.fetchEmployees();
                });
        return listCompletableFuture.get();
    }

    /**
     * Here we are using supplyAsync(Supplier<T>, Executor method) method, and it gets thread from custom executor
     * which we created using the Executor
     */
    public List<Employee> getEmployeesWithCustomExecutor() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listCompletableFuture =
                CompletableFuture
                        .supplyAsync(() -> {
                            System.out.println("Executed By Thread: "+Thread.currentThread().getName());
                            return EmployeeDatabase.fetchEmployees();
                        }, executor);
        return listCompletableFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        List<Employee> employeesWithCustomExecutor = supplyAsyncDemo.getEmployeesWithCustomExecutor();
        System.out.println(employees.size());
        System.out.println(employeesWithCustomExecutor.size());
    }
}
