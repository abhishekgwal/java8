package com.interview;

import java.util.*;
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
        //String concateListOfString = String.join("-", stringList);
        System.out.println(concateListOfString);

        // 6. Find only duplicates with its count from the String ArrayList
        List<String> listOfDuplicates = Arrays.asList("Python", "Java", "Java", "Scala", "C++", "PHP", "Java", "C++", "Python");
        Map<String, Long> occurrenceCount = listOfDuplicates.stream().filter(t -> Collections.frequency(listOfDuplicates, t) > 1).
                collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        System.out.println(occurrenceCount);

        // 7. Given a list of integers, find out all the even numbers from the list
        List<Integer> integerList = Arrays.asList(10,15,8,49,25,98,32);
        List<Integer> evenList = integerList.stream().filter(t -> t % 2 == 0).collect(Collectors.toList());
        System.out.println("Even numbers from the list " +evenList);

        // 8. Given a list of integers, find out all the numbers starting with 1
        List<Integer> listOfInteger = Arrays.asList(10,15,8,49,25,98,32);
        List<Integer> listOfIntegerStartingWith1 = listOfInteger.stream().filter(t -> Integer.toString(t).startsWith("1")).collect(Collectors.toList());
        System.out.println("List of integers starting with 1 "+listOfIntegerStartingWith1);

        // 9a. Find duplicates from a given list of integers - Method 1
        List<Integer> integersList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Map<Integer, Long> integersDuplicateCount = integersList.stream().filter(t -> Collections.frequency(integersList, t) > 1)
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        System.out.println("Duplicates from the given list "+integersDuplicateCount);

        // 9b. Find duplicates from a given list of integers - Method 2
        List<Integer> integersList2 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Set<Integer> set = new HashSet<>();
        List<Integer> duplicateIntList = integersList2.stream().filter(t -> !set.add(t)).collect(Collectors.toList()); // !set.add(t) - Breakdown explained in comment below
        System.out.println("Duplicates from the given list using set "+duplicateIntList);

        // 10. Remove whitespaces from String
        String str = "  This    is a sample String which   has    white spaces  ";
        String stringWithoutSpaces = str.chars().filter(c -> c != ' ') // .chars() returns an IntStream of Unicode code points.
                .mapToObj(c -> (char) c) // converts the IntStream to a Stream<Character>.
                .map(c -> c.toString()) // converts each Character to its string representation.
                .collect(Collectors.joining()); // join the characters into a single string.
        System.out.println("String after removing whitespaces "+stringWithoutSpaces);

        // 11. Find the maximum value present in the list
        List<Integer> findMaxList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Integer maxValue = findMaxList.stream().max(Comparator.comparing(t -> t)).get();
        System.out.println("MaxValue from the given list "+maxValue);

        // 12a. Sort the list of integers in ascending order
        List<Integer> unsortedList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        List<Integer> sortedListAscOrder = unsortedList.stream().sorted().collect(Collectors.toList());
        System.out.println("List of sorted elements in ascending order "+sortedListAscOrder);

        // 12b. Sort the list of integers in descending order
        List<Integer> sortedListDescOrder = unsortedList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        System.out.println("List of sorted elements in descending order "+sortedListDescOrder);

        // 13. Given an integer array, return true if any value appears at least twice in the array, and return false if every element is distinct.
        int[] duplicateNums = {1,3,2,3,4,5};
        List<Integer> duplicateNumsList = Arrays.stream(duplicateNums).boxed().collect(Collectors.toList());
        Set<Integer> setList = new HashSet<>(duplicateNumsList);
        System.out.println("Does the list has any duplicates ");
        System.out.println(setList.size() == duplicateNumsList.size());

        // 14. Count the occurrence of each character in a string
        String s = "ilovejavacoding";
        Map<String, Long> occurrenceCountForGivenString = Arrays.stream(s.split("")).collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        System.out.println("Occurrence of each character in a string "+occurrenceCountForGivenString);

        // 15. Find all duplicate element from a given string
        String s2 = "ilovetocodeinstyle";
        Map<String, List<String>> stringListMap = Arrays.stream(s2.split(""))
                .collect(Collectors.groupingBy(t -> t));
        List<String> duplicateElements = stringListMap.entrySet().stream().filter(t -> t.getValue().size() >= 2).map(t -> t.getKey()).collect(Collectors.toList());
        System.out.println("Duplicate elements from the given string "+duplicateElements);

        // 16. Find first non-repeating element from a String
        String s3 = "ilovetocodeinstyle";
        Map<String, Long> stringMap = Arrays.stream(s3.split("")).collect(Collectors.groupingBy(t -> t, LinkedHashMap::new, Collectors.counting()));
        String firstNonRepeatingElement = stringMap.entrySet().stream().filter(t -> t.getValue() == 1).findFirst().get().getKey();
        System.out.println("First non-repeating element " +firstNonRepeatingElement);

        // 17. Find the longest string from a given array
        String[] strArray = {"java", "python", "microservices", "springboot"};
        List<String> strList = Arrays.stream(strArray).collect(Collectors.toList());
        String longestString = strList.stream().reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();
        System.out.println("Longest string from a given array "+longestString);

    }
}

/*
        How !set.add(t) is working ?

        Set<Integer> set1 = new HashSet<>();
          List<Integer> list1 = new ArrayList<>();
          for (Integer i : integersList2) {
              boolean isDuplicateFound = !set1.add(i);
              if (isDuplicateFound)
                  list1.add(i);
          }
        System.out.println(list1);
 */

