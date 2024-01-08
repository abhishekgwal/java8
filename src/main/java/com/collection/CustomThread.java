package main.java.com.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomThread extends Thread {

    private static Map<Integer, String> map = new HashMap<>(); // Replace with ConcurrentHashMap for consistent behavior

    public void run() {
        try {
            Thread.sleep(1000);
            map.put(4, "d");
        } catch (InterruptedException e) {
            System.out.println("Child thread adding element");
        }
    }


    public static void main(String[] args) throws InterruptedException {

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        CustomThread customThread = new CustomThread();
        customThread.start();

        // Iterating main thread
        for (Object o : map.entrySet()) {
            Object s = o;
            System.out.println(s);
            Thread.sleep(1000);
        }
        System.out.println(map);
    }
    // Segment locking or bucket locking
}
