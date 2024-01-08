package main.java.com.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FailFastMapExample {

    public static void main(String[] args) {

        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        for (Map.Entry<Integer, String> item : map.entrySet()) {
            System.out.println(item.getKey() + ", " + item.getValue());
            map.put(4, "d");
        }
    }
}
