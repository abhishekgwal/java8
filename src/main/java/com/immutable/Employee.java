package main.java.com.immutable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Rules to define class as immutable
 * 1. Make class as final
 * 2. All instance variables as private and final
 * 3. No setter methods
 * 4. Initialize variables in constructor
 * 5. Perform cloning of mutable objects while returning from getter method
 */
public final class Employee {

    private final String name;
    private final Date doj;
    private final List<String> mobile;

    private final Address address;

    public Employee(String name, Date doj, List<String> mobile, Address address) {
        this.name = name;
        this.doj = doj;
        this.mobile = mobile;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Date getDoj() {
        return (Date) doj.clone();
    }

    public List<String> getMobile() {
        return new ArrayList<>(mobile);
    }

    public Address getAddress() {
        return new Address(address.getCity(), address.getZip());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", doj=" + doj +
                ", mobile=" + mobile +
                ", address=" + address +
                '}';
    }

    public static void main(String[] args) {

        Address address = new Address("Toronto", "33323");
        Date date = new Date();

        Employee employee = new Employee("Abhishek", date, Arrays.stream(new String[]{"1234, 5678"}).collect(Collectors.toList()), address);

        employee.getDoj().setTime(10);
        employee.getMobile().add("9010");
        employee.getAddress().setCity("Vancouver");
        employee.getAddress().setZip("5435454");
        System.out.println(employee);
    }
}
