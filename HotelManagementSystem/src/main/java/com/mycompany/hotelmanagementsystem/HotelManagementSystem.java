/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.hotelmanagementsystem;

import org.w3c.dom.DOMImplementation;

/**** ENTER HERE YOUR DETAILS:*******
         * 
         * FIRST NAME:Harindu
         * 
         * LAST NAME:Adikari
         * 
         * STUDENT ID:20220808
         * 
         */

public class HotelManagementSystem  {

    public static void main(String[] args) {
        
        HotelManager hotelManager = new WestminsterHotelManager(100);
        boolean exit = false;
        while (!exit) {
            exit = hotelManager.runMenu();
        }
    }
}
