package dev.velasquez.javatest.util;

public class FizzBuzz {
    public static String fizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) return "FizzBuzz";
        if (number % 3 == 0) return "Fizz";
        return number % 5 == 0 ? "Buzz" : String.valueOf(number);
    }
}
