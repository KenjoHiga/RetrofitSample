package com.kenjo_coding.androiddevtemplate.test_sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void test_execute_1() {
        String result = FizzBuzz.execute(1);
        assertEquals("1", result);
    }

    @Test
    public void test_execute_3() {
        String result = FizzBuzz.execute(3);
        assertEquals("Fizz", result);
    }
}