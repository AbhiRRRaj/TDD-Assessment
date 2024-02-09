package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        String numbersWithoutDelimiter = numbers;

        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(numbers);
            if (matcher.find()) {
                delimiter = matcher.group(1);
                numbersWithoutDelimiter = matcher.group(2);
            }
        }

        String[] numberArray = numbersWithoutDelimiter.split("[,\n" + delimiter + "]");
        int total = 0;
        List<String> negatives = new ArrayList<>();

        for (String number : numberArray) {
            int num = Integer.parseInt(number);
            if (num < 0) {
                negatives.add(number);
            } else {
                total += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + String.join(", ", negatives));
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(add(""));
        System.out.println(add("1"));
        System.out.println(add("1,5"));
        System.out.println(add("1\n2,3"));
        System.out.println(add("//;\n1;2"));
    }
}
