/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.synchronization_in_java;


/**
 * Synchronization in Java
 * @author Nil Patel
 */
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronization_In_Java {
    static int sum = 0;                         // shared variable that both threads will update
    static Lock key = new ReentrantLock();      // creates a reentrantlock object
    
    public static void main(String[] args) {
        int[] Array = new int[100];     // array of 100 integers
        int[] firstHalf;                // array for first 50 elements
        int[] secondHalf;               // array for second 50 elements
        
        // Generates an Array whose 100 elements are 1,2, 3,... 100
        for (int i = 0; i < Array.length; i++) {
            Array[i] = i + 1;
        }
        
        // Prints the Array whose 100 elements are 1,2, 3,... 100
        System.out.println(Arrays.toString(Array));
        
        // Splits the Array in half
        firstHalf = Arrays.copyOfRange(Array, 0, Array.length/2);
        secondHalf = Arrays.copyOfRange(Array, Array.length/2, Array.length);
        
        // Prints the First Half and Second Half Array
        System.out.println("First Half Array: " + Arrays.toString(firstHalf));
        System.out.println("Second Half Array: " + Arrays.toString(secondHalf));
        
        /**
         * Repeats 100 times
         * Creates two threads for the class sumThread and uses them to calculate the summation of the 100 elements.
         * The First thread adds the first 50 elements to sum,
         * The Second thread add the second 50 elements to sum.
         * Then it Displays the Loop counter and the sum.
         */
        for (int i = 0; i < 100; i ++) {
            sumThread first50elements = new sumThread(firstHalf, key);
            sumThread second50elements = new sumThread(secondHalf, key);
            Thread thread0 = new Thread(first50elements);
            Thread thread1 = new Thread(second50elements);
            thread0.start();
            thread1.start();
            
            // Wait for both threads to finish before continuing
            try {
                thread0.join();
                thread1.join();
            } catch (InterruptedException ie) { }
            
            System.out.println("Loop: " + i + " Sum: " + sum);
            
            // Resets the sum to 0
            sum = 0;
        }
        
    }
    
    static class sumThread implements Runnable {
        int[] toDoArray;        // array segment assigned to this thread.
        Lock key;               // shared key passed from main class
        
        // Constructor
        public sumThread(int[] toDoArray, Lock key) {
            this.toDoArray = toDoArray;
            this.key = key;
        }

        @Override
        public void run() {
            
            // Calculates the summation of 50 elements. adds it to localsum
            int localsum = 0;
            for (int i = 0; i < toDoArray.length; i++) {
                localsum += toDoArray[i];
            }
            
            // pause thread for 5 millisecond
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) { }
            
            
            key.lock();         // locks so no other thread can use this until done.
            try {
                sum += localsum;
            } finally {
                key.unlock();   // releases lock for next thread.
            }
        }
    }
}