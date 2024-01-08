package main.java.com.collection.comparator;

import main.java.com.model.Student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return (s1.getName().compareTo(s2.getName()));
    }
}
