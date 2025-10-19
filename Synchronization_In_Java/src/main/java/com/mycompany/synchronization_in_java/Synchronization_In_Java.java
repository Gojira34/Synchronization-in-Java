/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.synchronization_in_java;


/**
 *
 * @author voyager
 */
import java.util.Arrays;

public class Synchronization_In_Java {

    public static void main(String[] args) {
        int sum = 0;
        int[] Array = new int[101];
        int[] firstHalf;
        int[] secondHalf;
        
        for (int i = 0; i < Array.length; i++) {
            Array[i] = i;
        }
        System.out.println(Arrays.toString(Array));
        firstHalf = Arrays.copyOfRange(Array, 0, Array.length/2);
        secondHalf = Arrays.copyOfRange(Array, Array.length/2, Array.length);
        System.out.println("First Half Array: " + Arrays.toString(firstHalf));
        System.out.println("Second Half Array: " + Arrays.toString(secondHalf));
//        
//        
//        
//        
//        for (int i = 0; i < Array.length; i++) {
//            OGsum += Array[i];
//            System.out.println("OG Sum: " + OGsum);
//        }
//        for (int i = 0; i < firstHalf.length; i++) {
//            firstSum += firstHalf[i];
//            System.out.println("first half Sum: " + firstSum);
//        }
//        
//        for (int i = 0; i < secondHalf.length; i++) {
//            secondSum += secondHalf[i];
//            System.out.println("Second half Sum: " + secondSum);
//        }
//        
        for (int i = 0; i <= 100; i ++) {
            sumThread first50elements = new sumThread(firstHalf, sum);
            sumThread second50elements = new sumThread(secondHalf, sum);
            Thread thread0 = new Thread(first50elements);
            Thread thread1 = new Thread(second50elements);
            thread0.start();
            thread1.start();
        
            try {
                // Wait for both threads to finish before continuing
                thread0.join();
                thread1.join();
            } catch (InterruptedException ie) {
            }
            System.out.println("Loop: " + i);
        }
        
    }
}

class sumThread implements Runnable {
    private static int sumTotal;
    private int[] toDoArray;
    
    
    public sumThread (int[] toDoArray, int sumTotal) {
        this.toDoArray = toDoArray;
        this.sumTotal = sumTotal;
    }
    
    @Override
    public void run() { 
        for (int i = 0; i < toDoArray.length; i++) {
            sumTotal += toDoArray[i];
        }
        System.out.println("Sum: " + sumTotal);
    }
    
    public int getSum() {
        return sumTotal;
    }
}