package main.java.com.interview;

import main.java.com.model.Player;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8CommonProgrammingQA {

    public static void main(String[] args) {

        List<Player> playerList = Stream.of(
                new Player(1, "Rohit", 30, "Male", "Batter", "Mumbai", 12, Arrays.asList("12345", "54896"), 45000),
                new Player(2, "Shubman", 22, "Male", "Batter", "Delhi", 18, Arrays.asList("78542", "68745"), 80000),
                new Player(3, "Virat", 35, "Male", "Batter", "Bangalore", 1, Arrays.asList("58542", "68745"), 120000),
                new Player(4, "Rahul", 29, "Male", "Wicket Keeper", "Punjab", 9, Arrays.asList("65893", "74561"), 10000),
                new Player(5, "Jadeja", 32, "Male", "Fielder", "Chennai", 12, Arrays.asList("78542", "68745"), 100000),
                new Player(6, "SuryaKumar", 26, "Male", "All Rounder", "Mumbai", 21, Arrays.asList("96025", "89742", "78945"), 200000),
                new Player(7, "Shami", 35, "Male", "Baller", "Delhi", 6, Arrays.asList("96710", "63987", "45780", "457851"), 900000),
                new Player(8, "Bumrah", 32, "Male", "Baller", "Mumbai", 4, Arrays.asList("98631", "98531"), 1_25000),
                new Player(9, "Kuldeep", 29, "Male", "Baller", "Delhi", 41, Arrays.asList("78542", "74126"), 1_80000),
                new Player(10, "Siraj", 26, "Male", "Baller", "Bangalore", 31, Arrays.asList("98537", "74392"), 2_25000),
                new Player(11, "Hardik", 33, "Male", "All Rounder", "Gujarat", 31, Arrays.asList("74580", "62500"), 78500)
        ).collect(Collectors.toList());

        // 1. Find list of players whose rank is between 1 and 10
        List<Player> playerRankUnderTen = playerList.stream().filter(player -> player.getRank() >= 1 && player.getRank() <= 10).collect(Collectors.toList());
        System.out.println("List of players whose rank is between 1 and 10 " +playerRankUnderTen);

        System.out.println();System.out.println();

        // 2. Find list of players from Mumbai and then sort their names in ascending order
        List<Player> playersFromMumbaiInAscendingOrder = playerList.stream().filter(player -> player.getCity().equals("Mumbai")).sorted(Comparator.comparing(player -> player.getFirstName())).collect(Collectors.toList());
        System.out.println("List of players from Mumbai in ascending order "+playersFromMumbaiInAscendingOrder);

        System.out.println();System.out.println();

        // 3. Find list of players from Mumbai and then sort their names in descending order
        List<Player> playersFromMumbaiInDescendingOrder = playerList.stream().filter(player -> player.getCity().equals("Mumbai")).sorted(Comparator.comparing(player -> player.getFirstName(), Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("List of players from Mumbai in descending order "+playersFromMumbaiInDescendingOrder);

        System.out.println();System.out.println();

        // 4. Find all the departments - Notice distinct keyword which removes duplicates
        List<String> departmentList = playerList.stream().map(player -> player.getDept()).distinct().collect(Collectors.toList());
        System.out.println("Players list of departments " +departmentList);
        /**
         * Another solution - To avoid duplicates you may also use set
           Set<String> departmentSet = playerList.stream().map(Player::getDept).averageAgeOfPlayers(Collectors.toSet());
         *
         */

        System.out.println();System.out.println();

        // 5. Find all the contact numbers
        List<String> contactList = playerList.stream().flatMap(player -> player.getContacts().stream()).distinct().collect(Collectors.toList());
        System.out.println("List of contacts for all players "+contactList);

        System.out.println();System.out.println();

        // 6. Group players by department names
        Map<String, List<Player>> playersByDeptName = playerList.stream().collect(Collectors.groupingBy(player -> player.getDept()));
        System.out.println("Group players by department names " +playersByDeptName);

        System.out.println();System.out.println();

        // 7. Count players from each department
        Map<String, Long> collected = playerList.stream().collect(Collectors.groupingBy(player -> player.getDept(), Collectors.counting()));
        System.out.println("No of players in each department "+collected);

        System.out.println();System.out.println();

        // 8. Find average age of all players
        Map<String, Double> averageAgeOfPlayers = playerList.stream().collect(Collectors.groupingBy(player -> player.getGender(), Collectors.averagingInt(player -> player.getAge())));
        System.out.println("Average age of all players "+averageAgeOfPlayers);

        System.out.println();System.out.println();

        // 9. Find the best player in each department based on their rank. The lower the rank the best they are.
        Map<String, Optional<Player>> playerMap = playerList.stream().collect(Collectors.groupingBy(player -> player.getDept(), Collectors.minBy(Comparator.comparing(player -> player.getRank()))));
        System.out.println("Best player in each department based on their rank "+playerMap);

        System.out.println();System.out.println();

        // 10. Players with highest salary in each department
        Comparator<Player> compareBySalary = Comparator.comparing(Player::getSalary);
        Map<String, Optional<Player>> employeeMapBySalary = playerList.stream().collect(Collectors.groupingBy(Player::getDept, Collectors.reducing(BinaryOperator.maxBy(compareBySalary))));
        System.out.println("Players with highest salary in each department " +employeeMapBySalary);
    }
}