/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagementsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class HotelStaff implements Comparable<HotelStaff> {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String staffID;

    public HotelStaff(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Setter and Getter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getStringDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateOfBirth.format(formatter);
    }

    @Override
    public String toString() {
        return name + " " + surname + ", ID: " + staffID + ", DOB: " + getStringDate();
    }

    /**
     * Compares this HotelStaff to another HotelStaff based on age.
     * Younger staff members come before older staff members in the natural ordering.
     * 
     * @param other the HotelStaff to be compared
     * @return a negative integer if this staff is younger, 
     *         a positive integer if this staff is older, 
     *         or zero if they have the same date of birth
     */
    @Override
    public int compareTo(HotelStaff other) {
        // For sorting from youngest to oldest
        // Later dates (younger people) should come first, so we reverse the natural ordering
        // by comparing other's date to this date (instead of this date to other's date)
        if (this.dateOfBirth == null && other.dateOfBirth == null) {
            return 0; // Both null, considered equal
        } else if (this.dateOfBirth == null) {
            return 1; // Null is considered greater (older)
        } else if (other.dateOfBirth == null) {
            return -1; // Null is considered greater (older)
        }

        // Compare dates - reverse the comparison to get youngest first
        return other.dateOfBirth.compareTo(this.dateOfBirth);
    }
}
