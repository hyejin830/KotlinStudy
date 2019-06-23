package com.example.kotlinstudyproject.chapter3;

import android.view.View;

import java.util.ArrayList;


import static com.example.kotlinstudyproject.chapter3.KthreeTwoKt.joinToString;
import static com.example.kotlinstudyproject.chapter3._3_3Kt.visible;


public class ThreeTwo {


    public static void main(String arg[]){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        System.out.println(joinToString(arr,";","[","]"));

        View v = new View(null);
        visible(v, true);
    }






}
