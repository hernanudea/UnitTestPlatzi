package dev.velasquez.javatest.util;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import static org.junit.Assert.*;

/*
Usando TDD (empieza por los tests), implementa una función que dado un número:
    🥇 Si el número es divisible por 3, retorna “Fizz”
    🥈 Si el número es divisible por 5, retorna “Buzz”
    🥉 Si el número es divisible por 3 y por 5, retorna “FizzBuzz”
    En otro caso, retorna el mismo número
 */

public class FizzBuzzShould {
    @Test
    public void returnFizzWhenIsDivisibleBy3() {
        assertThat(FizzBuzz.fizzBuzz(3), is("Fizz"));
        assertThat(FizzBuzz.fizzBuzz(99), is("Fizz"));
        assertThat(FizzBuzz.fizzBuzz(351), is("Fizz"));
        assertThat(FizzBuzz.fizzBuzz(525441), is("Fizz"));
    }

    @Test
    public void returnBuzzWhenIsDivisibleBy5() {
        assertThat(FizzBuzz.fizzBuzz(5), is("Buzz"));
        assertThat(FizzBuzz.fizzBuzz(100), is("Buzz"));
        assertThat(FizzBuzz.fizzBuzz(350), is("Buzz"));
        assertThat(FizzBuzz.fizzBuzz(525440), is("Buzz"));
    }

    @Test
    public void returnFizzBuzzWhenIsDivisibleBy3And5() {
        assertThat(FizzBuzz.fizzBuzz(15), is("FizzBuzz"));
        assertThat(FizzBuzz.fizzBuzz(165), is("FizzBuzz"));
        assertThat(FizzBuzz.fizzBuzz(360), is("FizzBuzz"));
        assertThat(FizzBuzz.fizzBuzz(526440), is("FizzBuzz"));
    }
}