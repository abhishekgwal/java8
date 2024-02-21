package com.interview;

import com.model.Employee;
import com.model.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFactoryImpl {

    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        // 1. List all distinct project in non-ascending order.
        List<Project> distinctSortedProjectsInDescendingOrder = employeeList.stream().flatMap(t -> t.getProjects().stream()).sorted(Comparator.comparing(Project::getName).reversed()).distinct().collect(Collectors.toList());
        System.out.println(distinctSortedProjectsInDescendingOrder);

        System.out.println();System.out.println();

        // 2. Print full name of any employee whose firstName starts with ‘A’.
        List<Employee> listOfEmployeesStartingWithNameA = employeeList.stream().filter(t -> t.getFirstName().startsWith("A")).collect(Collectors.toList());
        List<String> finalListNameWithA = listOfEmployeesStartingWithNameA.stream().map(t -> t.getFirstName().concat(" "+t.getLastName())).collect(Collectors.toList());
        System.out.println(finalListNameWithA);

        System.out.println();System.out.println();

        // 3. List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)
        List<Employee> employeesJoinedIn2023 = employeeList.stream().filter(t -> t.getId().startsWith("2023")).collect(Collectors.toList());
        System.out.println(employeesJoinedIn2023);

        System.out.println();System.out.println();

        // 4. Sort employees based on firstName, for same firstName sort by salary
        List<Employee> sortingOnEmployeeFirstName = employeeList.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
        System.out.println(sortingOnEmployeeFirstName);

        System.out.println();System.out.println();

        List<Employee> sortBySalary = sortingOnEmployeeFirstName.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());
        System.out.println(sortBySalary);

        // 5. Print names of all employee with 3rd highest salary


        // 6. Print min salary
        int minSalary = employeeList.stream().min(Comparator.comparing(Employee::getSalary)).get().getSalary();
        System.out.println(minSalary);

        // 7. Print list of all employee with min salary.
        List<Employee> employeeListWithMinSalary = employeeList.stream().filter(employee -> employee.getSalary() == minSalary).collect(Collectors.toList());
        System.out.println(employeeListWithMinSalary);

        // 8. List of people working on more than 2 projects.
        List<Employee> employeesOnMoreThanTwoProjects = employeeList.stream().filter(t -> t.getProjects().size() > 2).collect(Collectors.toList());
        System.out.println("List of people working on more than 2 projects === "+employeesOnMoreThanTwoProjects);

        // 9. Count of total laptops assigned to the employees.
        int laptopTotalCount = employeeList.stream().mapToInt(Employee::getTotalLaptopsAssigned).sum();
        System.out.println(laptopTotalCount);

        // 10. Count of all projects with Robert Downey Jr as PM.
        List<Project> listOfProjects = employeeList.stream().flatMap(t -> t.getProjects().stream()).collect(Collectors.toList());
        List<Project> robertDowneyJrProjects = listOfProjects.stream().filter(t -> t.getProjectManager().equals("Robert Downey Jr")).distinct().collect(Collectors.toList());
        System.out.println("Count of all projects with Robert Downey Jr as PM - "+robertDowneyJrProjects.size());

        // 11. List of all projects with Robert Downey Jr as PM
        System.out.println("List of all projects with Robert Downey Jr as PM "+robertDowneyJrProjects);

        // 12. List of all people working with Robert Downey Jr.
        List<Employee> employeesUnderRobertDowneyJr = employeeList.stream()
                .filter(t -> t.getProjects().stream().anyMatch(p -> p.getProjectManager().equals("Robert Downey Jr")))
                .collect(Collectors.toList());
        System.out.println("List of all people working with Robert Downey Jr. "+employeesUnderRobertDowneyJr);

        // 13. Create a map based on this data, the key should be the year of joining, and value should be list of all the employees who joined the particular year.

        Map<String, List<Employee>> employeeListMapByYear = employeeList.stream().collect(Collectors.groupingBy(t -> t.getId().substring(0, 4)));
        System.out.println(employeeListMapByYear);

        // 14. Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year.
        employeeList.stream().collect(Collectors.groupingBy(t -> t.getId().substring(0, 4)));

    }
}
