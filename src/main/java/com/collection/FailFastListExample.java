package main.java.com.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastListExample {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");

        for (String s : list) {
            System.out.println(s);
            list.add("c");
        }
        System.out.println(list);
    }
}
