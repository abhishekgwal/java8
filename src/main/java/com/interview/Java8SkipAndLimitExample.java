package com.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8SkipAndLimitExample {

    public static void main(String[] args) {

        // 1. Find second-highest number from given array
        int[] nums = {2, 6, 12, 54, 7};
        List<Integer> reverseOrderSortedList = Arrays.stream(nums).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        int secondHighestNumber = reverseOrderSortedList.stream().skip(1).findFirst().get();
        System.out.println("Second highest number from given array "+secondHighestNumber);

        // 2. You have nos from 1 to 10. Skip the first and last element (2-9)
        IntStream.range(1, 10).skip(1).limit(9).forEach(System.out::println);

        /**
         * If it was an array:
         * int[] numbers = {1,2,3,4,5,6,7,8,9,10};
         * Arrays.stream(numbers).boxed().skip(1).limit(8).forEach(System.out::println);
         */

    }
}
