package com.kenjo_coding.androiddevtemplate.test_sample;

public class FizzBuzz {
    static String execute(int i){
        if(i % 3 == 0){
            return "Fizz";
        }
        return String.valueOf(i);
    }

}
