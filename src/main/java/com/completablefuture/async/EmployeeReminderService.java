package com.completablefuture.async;

import com.completablefuture.database.EmployeeDatabase;
import com.completablefuture.dto.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * In below program we are sending emails to all new employees whose training is pending. We are using 5 threads and each
   thread is responsible for doing one task.

  What have we done at each thread:
  1. Get all employees from the database
  2. Filter out all new joined employees
  3. Check if training is pending for employee
  4. Get employees email id
  5. Send reminder email to all this employee

 Things to Remember:
 * thenApply() -> Runs on a single thread.

 * thenApplyAsync() -> Runs on multiple threads. In below program, we provided ThreadPool size of 5, and we are using
 * executor service in 5 different task. Therefore, each task was taken by separate thread.
 *
 * thenRun()/thenAccept() -> If you don't want to return anything after your thread execution in a single pipeline then
 * you can go with thenRun()/thenAccept()
 *
 * Also notice, how we have chained multiple futures together. That is one advantage of CompletableFuture
 */
public class EmployeeReminderService {

    public CompletableFuture<Void> sendReminderToEmployee() {

        Executor executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetch Employees from Database : " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Filter new joiner employee : " + Thread.currentThread().getName());
            return employees.stream().filter(employee -> "TRUE".equals(employee.getNewJoiner())).collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Filter training not completed by employee : " + Thread.currentThread().getName());
            return employees.stream().filter(employee -> "TRUE".equals(employee.getLearningPending())).collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Get emails : " + Thread.currentThread().getName());
            return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
        }, executor).thenAcceptAsync((emails) -> {
            System.out.println("Sent emails : " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderService::sendEmail);
        }, executor);
        return voidCompletableFuture;
    }

    public static void sendEmail(String email) {
        System.out.println("Sending training emails reminder to : "+email);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderService employeeReminderService = new EmployeeReminderService();
        employeeReminderService.sendReminderToEmployee().get();
    }
}
