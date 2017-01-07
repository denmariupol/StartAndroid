package com.example.lesson019_calculator;

import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;

/**
 * Created by den on 2016-12-28.
 */

class Operation {

    public static BigDecimal Addition(){

        return Numbers.numbers.getFirst().add(Numbers.numbers.getLast());
    }

    public static BigDecimal Subtraction(){
        return Numbers.numbers.getFirst().subtract(Numbers.numbers.getLast());
    }

    public static BigDecimal Division(){
        return Numbers.numbers.getFirst().divide(Numbers.numbers.getLast());
    }

    public static BigDecimal Multiply(){
        return Numbers.numbers.getFirst().multiply(Numbers.numbers.getLast());
    }
}
