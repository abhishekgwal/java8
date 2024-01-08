package main.java.com.collection.comparator;

import main.java.com.model.Student;

import java.util.Comparator;

public class IdComparator implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        if (student1.getId() == student2.getId())
            return 0;
        else if (student1.getId() > student2.getId())
            return 1;
        else
            return -1;
    }
}
