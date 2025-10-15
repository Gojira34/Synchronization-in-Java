/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.synchronization_in_java;

/**
 *
 * @author voyager
 */
public class Synchronization_In_Java {

    public static void main(String[] args) {
        int sum = 0;
        
        for (int i = 0; i <= 100; i++) {
            sum += i;
            System.out.println("Loop " + i + ": " + sum);
        }
    }
}
