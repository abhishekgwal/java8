package main.java.com.collection;

import java.util.ArrayList;

/**
 * Custom array list which does not allow duplicates
 */

public class CustomArrayList extends ArrayList {

    @Override
    public boolean add(Object o) {
        if (this.contains(o))
            return true;
        else
            return super.add(o);
    }

    public static void main(String[] args) {

        CustomArrayList list = new CustomArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);

        System.out.println(list);
    }
}
