package com.example.lesson019_calculator;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by den on 2016-12-28.
 */

class Operation {

    public static float Addition(){

        return Numbers.numbers.getFirst() + Numbers.numbers.getLast();
    }

    public static float Subtraction(){
        return Numbers.numbers.getFirst() - Numbers.numbers.getLast();
    }

    public static float Division(){
        return Numbers.numbers.getFirst() / Numbers.numbers.getLast();
    }

    public static float Multiply(){
        return Numbers.numbers.getFirst() * Numbers.numbers.getLast();
    }
}
