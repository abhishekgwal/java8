package main.java.com.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8InterviewPractice {

    public static void main(String[] args) {

        // 1. Convert lowercase to uppercase
        List<String> list = Arrays.asList("aa", "bb", "cc", "java", "python", "abhishek");
        List<String> upperCaseList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperCaseList);

        // 2. Count occurrence of each word in a list
        List<String> listOfLanguages = Arrays.asList("Python", "Java", "Java", "Scala", "C++", "PHP", "Java", "C++", "Python");
        Map<String, Long> languageListMap = listOfLanguages.stream().collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        System.out.println(languageListMap);

        // 3a.  Replace true with Yes and False with No from a given boolean list
        List<Boolean> booleanList = Arrays.asList(true, false, true, true, true, false, false);
        List<String> yesNoList = booleanList.stream().map(t -> t ? "Yes" : "No").collect(Collectors.toList());
        System.out.println(yesNoList);

        // 3b. Remove all No's from the list
        List<String> yesListOnly = yesNoList.stream().filter(t -> t.equals("Yes")).collect(Collectors.toList());
        System.out.println(yesListOnly);

        // 4a. Convert primitive array to List, then sort in ascending and descending order
        int[] nums = {3,5,7,3,5,12,45};
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("Primitive array to List " +numsList);

        // 4b. Sorting in ascending order
        List<Integer> sortedListInAscendingOrder = numsList.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted List in Decreasing order "+ sortedListInAscendingOrder);

        // 4c. Sorting in descending order
        List<Integer> sortedListInDecreasingOrder = numsList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        System.out.println("Sorted List in Decreasing order "+ sortedListInDecreasingOrder);

        // 5. Concatenate List of String and separate with a delimiter
        List<String> stringList = Arrays.asList("Welcome", "to", "Java");
        String concateListOfString = stringList.stream().collect(Collectors.joining("-"));
        System.out.println(concateListOfString);

        // 6. Find only duplicates with its count from the String ArrayList
        List<String> listOfDuplicates = Arrays.asList("Python", "Java", "Java", "Scala", "C++", "PHP", "Java", "C++", "Python");
        Map<String, Long> occurrenceCount = listOfDuplicates.stream().filter(t -> Collections.frequency(listOfDuplicates, t) > 1).
                collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        System.out.println(occurrenceCount);

    }
}

