package main.java.com.model;

import java.util.List;
import java.util.Objects;


public class Player {

    private int id;
    private String firstName;
    private int age;
    private  String gender;
    private String dept;
    private String city;
    private int rank;
    private List<String> contacts;
    private int salary;

    public Player(int id, String firstName, int age, String gender, String dept, String city, int rank, List<String> contacts, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
        this.dept = dept;
        this.city = city;
        this.rank = rank;
        this.contacts = contacts;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return salary == player.salary && id == player.id && age == player.age && rank == player.rank && Objects.equals(firstName, player.firstName) && Objects.equals(gender, player.gender) && Objects.equals(dept, player.dept) && Objects.equals(city, player.city) && Objects.equals(contacts, player.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, age, gender, dept, city, rank, contacts, salary);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", dept='" + dept + '\'' +
                ", city='" + city + '\'' +
                ", rank=" + rank +
                ", contacts=" + contacts +
                ", salary=" + salary +
                '}';
    }
}
