package main.java.com.collection;

import main.java.com.collection.comparator.NameComparator;
import main.java.com.model.Student;

import java.util.*;

public class StudentImpl {

    public static void main(String[] args) {

        // Sorting using comparable
        List<Student> studentList  = new ArrayList<>();
        Student s1 = new Student(15, "David");
        Student s2 = new Student(12, "Warner");
        Student s3 = new Student(9, "John");
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        Collections.sort(studentList);
        System.out.println("Sorted Id's using comparable "+studentList);

        // Sorting using comparator
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(s1);
        studentList1.add(s2);
        studentList1.add(s3);

        Collections.sort(studentList1, new NameComparator());
        System.out.println("Sorted Names using Comparator "+studentList1);

    }
}
